import random

from src.exceptions import *



class Service:
    def __init__(self, year):
        self.current_year = year
        self.lost = False




    def buy_acres(self, nr_of_acres_to_buy):
        """
        Function to buy acres. Raises an exception if you dont have enough grains to be able to buy that
        many acres
        If you do have enough grains, it updates the acres and it deletes the nr of stocks used to buy the acres.
        :param nr_of_acres_to_buy: integer
        :return:
        """

        if self.current_year.price * nr_of_acres_to_buy > self.current_year.grain_stocks:
            raise MyExcepion("Not enough grains for you to be able to buy, sorry!")
        else:
            self.current_year.acres = self.current_year.acres + self.current_year.price
            self.current_year.grain_stocks -= nr_of_acres_to_buy * self.current_year.price



    def sell_acres(self, nr_of_acres_to_sell):
        """
               Function to sell acres. Raises an exception if you dont have enough grains to sell.

               If you do have enough grains, it updates the grains  and it updates the nr of acres.
               :param nr_of_acres_to_sell: integer
               :return:
               """
        if abs(nr_of_acres_to_sell) > self.current_year.acres:
            raise MyExcepion("You dont have enough grains to sell!")
        else:
            self.current_year.grain_stocks += abs(nr_of_acres_to_sell) * self.current_year.price
            self.current_year.acres += nr_of_acres_to_sell

    def feed_population(self, units):
        """
        Function that feeds the population
        it raises MyException if the given units are smaller than 0, if the units are bigger than the actual number
        of grain stocks available and if the user feed more people than there are actually needed to be feed
        if it doesnt raise exception, the stocks are updated, wich means we subtract from the current stocks the units that
        we used to feed people and we updated the nr of starved ppl by subtracting the number of ppl we feed from the
        current population.
        :param units: integer
        :return:
        """
        if units < 0:
            raise MyExcepion("You have to give a value that is bigger or equal to 0.")
        if units > self.current_year.grain_stocks:
            raise MyExcepion("Don't feed that many people...")
        if units // 20 > self.current_year.city_population: #each person needs 20 units to suvive, thats why we divide by 20
            raise MyExcepion("You cannot feed more people than you have.")
        self.current_year.grain_stocks -= units
        self.current_year.starved_people= self.current_year.city_population - units // 20



    def plant_acres(self, nr_of_acres):
        """
        Function that plants acres
        Raises exception  if the nr of given acres is bigger than the acres we curently have  and if the nr of acres
        is bigger than the stocks
        Else, It updates the plantes acres and it subtracts the nr of acres from the frain stocks
        :param nr_of_acres: int
        :return:
        """
        if nr_of_acres > self.current_year.acres:
            raise MyExcepion("You cannot plant more acres than you have")
        if nr_of_acres > self.current_year.grain_stocks:
            raise MyExcepion("You cannot plant more grains than you have")
        else:
            self.current_year.planted_acres = nr_of_acres
            self.current_year.grain_stocks -= nr_of_acres
    def next_year(self):
        """
        Function that goes to the next year
        It checks if the current year has starved people and it checks if the game is lost when more then 50 percent
        of the population dies
        Also it calculates if there is a rat infestation and if it does than they can eat up to 10% of the grain
        :return:
        """
        self.current_year.year += 1
        self.current_year.harvest = random.randint(1, 6)
        self.current_year.grain_stocks += self.current_year.planted_acres * self.current_year.harvest
        self.current_year.price= random.randint(15, 25)

        if self.current_year.starved_people == 0:
            new_comers = random.randint(0, 10)
            self.current_year.new_people = new_comers
            self.current_year.city_population += new_comers
        else:
            population = self.current_year.city_population
            self.current_year.city_population -= self.current_year.starved_people # take the people that died
            if self.current_year.city_population < population // 2:
                self.lost = True
            self.current_year.new_people = 0
        chance_of_20_percent = [False, False, False, False, True]
        rats = random.randint(0, 4)

        if  chance_of_20_percent[rats ] is True:
            self.current_year.rats = (random.choice([1, 2, 3, 4, 5, 6, 7, 8, 9, 10])) * self.current_year.grain_stocks // 100
            self.current_year.grain_stocks -= self.current_year.rats
        else:
            self.current_year.rats = 0







