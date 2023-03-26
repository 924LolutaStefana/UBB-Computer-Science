class CountryActivityAvgMoneyDTO:
    def __init__(self, country_id, country_name, avg_money):
        self.country_id=country_id
        self.country_name=country_name
        self.avg_money=avg_money
class SeasonVacationAvgGradeDTO:
    def __init__(self, vacation_id, vacation_name, avg_grade):
        self.vacation_id=vacation_id
        self.vacation_name=vacation_name
        self.avg_grade=avg_grade