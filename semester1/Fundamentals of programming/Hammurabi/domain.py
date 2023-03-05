class Year:
    def __init__(self):
        self.year = 1
        self.new_people = 0
        self.starved_people = 0
        self.harvest = 3
        self.rats = 200
        self.price = 20
        self.grain_stocks = 2800

        self.acres = 1000
        self.planted_acres = 0
        self.city_population = 100


    def __str__(self):
        """
        Str function that returns the string version of class Year that has all the information about the city
        :return: str
        """
        return f"In year {self.year}, {self.starved_people} people starved.\n {self.new_people} people came to the" \
               f" city.\n City population is  {self.city_population} \n City owns {self.acres} acres of land \n" \
               f"Harvest was {self.harvest} units per acre. \n Rats ate {self.rats} units. \n Land price is " \
               f"{self.price} units per acre.\n\n Grain stocks are {self.grain_stocks} units. "




