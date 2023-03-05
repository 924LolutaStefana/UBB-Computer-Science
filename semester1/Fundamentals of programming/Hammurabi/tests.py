from domain import *
from service import *


class Test:




    def test_feed_ppl(self):
        year = Year()
        service = Service(year)

        grains = service.current_year.grain_stocks
        service.feed_population(2000)
        assert service.current_year.starved_people == 0
        assert service.current_year.grain_stocks == grains - service.current_year.city_population * 20
    def test_buy_acres(self):
        year = Year()
        service = Service(year)
        acres = service.current_year.acres
        grains = service.current_year.grain_stocks
        service.buy_acres(1)

        assert service.current_year.acres == acres + service.current_year.price

        assert service.current_year.grain_stocks == grains - service.current_year.price
    def test_sell_acres(self):
        year = Year()
        service = Service(year)
        acres = service.current_year.acres
        grains = service.current_year.grain_stocks
        service.sell_acres(-1)

        assert service.current_year.acres == acres -1
        assert service.current_year.grain_stocks == grains +service.current_year.price


    def runAllTests(self):
        self.test_buy_acres()
        self.test_feed_ppl()
        self.test_sell_acres()






