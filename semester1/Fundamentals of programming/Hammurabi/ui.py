from src.service import *
from src.exceptions import *



class UI:
    def __init__(self, service):
        self.service = service


    def buy_acres_ui(self, nr_of_acres):
        try:
            check_numerical(nr_of_acres)
            self.service.buy_acres(int(nr_of_acres))


        except ValueError as ex:
            print(str(ex))
            self.read_acres_to_buy_or_sell()

        except MyExcepion as er:
            print(str(er))
            self.read_acres_to_buy_or_sell()

    def sell_acres_ui(self, nr_of_acres):
        try:
            check_numerical(nr_of_acres)
            self.service.sell_acres(int(nr_of_acres))


        except ValueError as ex:
            print(str(ex))
            self.read_acres_to_buy_or_sell()



    def feed_population_ui(self, units):
        try:
            check_numerical(units)
            self.service.feed_population(int(units))
        except Exception as ex:
            print(str(ex))
            self.read_units_to_feed()

    def plant_acres_ui(self, acres):
        try:
            check_numerical(acres)
            self.service.plant_acres(int(acres))
        except Exception as ex:
            print(str(ex))
            self.read_acres_to_plant()


    def read_acres_to_buy_or_sell(self):
        try:
            acres = int(input("Acres to buy/sell(+/-)->"))
            if int(acres) >= 0:
                self.buy_acres_ui(acres)
            else:
                self.sell_acres_ui(acres)
        except Exception as ex:
            print(str(ex))
            self.read_acres_to_buy_or_sell()












    def read_units_to_feed(self):
        units = input("Units to feed the population->")
        self.feed_population_ui(units)

    def read_acres_to_plant(self):
        acres = input("Acres to plant->")
        self.plant_acres_ui(acres)


    def start(self):
        for i in range(4): # 5 years
            self.read_acres_to_buy_or_sell()
            self.read_units_to_feed()
            self.read_acres_to_plant()
            self.service.next_year()
            print(str(self.service.current_year))
            if self.service.lost:
                print("GAME OVER! You did not do well.")
                return
        if self.service.current_year.acres >= 1000 and self.service.current_year.city_population >= 100:
            print("Congrats! You are the greatest mighty Hammurabi !")
        else:
            print("GAME OVER! You did not do well.")
