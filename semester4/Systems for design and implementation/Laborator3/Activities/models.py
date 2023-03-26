from django.db import models

# Create your models here.

class Season(models.Model):
    name=models.CharField(max_length=100)
    related_color=models.CharField(max_length=100)
    someone_born_on_this_month=models.CharField(max_length=100)
    a_grade=models.IntegerField()
    an_animal_that_likes_this_season=models.CharField(max_length=100)

    def __str__(self):
        return self.name

class Activity(models.Model):
    name=models.CharField(max_length=100)
    related_season=models.ForeignKey(Season, on_delete=models.CASCADE,related_name='children')
    a_person_to_complete_the_activity_with=models.CharField(max_length=100)
    money_required=models.IntegerField()
    time_required=models.CharField(max_length=100)
    countries = models.ManyToManyField('Country', through='Vacation')
    def __str__(self):
        return self.name
class Country(models.Model):
    name=models.CharField(max_length=100)
    population = models.CharField(max_length=100)
    surface=models.CharField(max_length=100)
    climate=models.CharField(max_length=100)
    the_predominant_religion=models.CharField(max_length=100)
    activities = models.ManyToManyField('Activity', through='Vacation')
    def __str__(self):
        return self.name
class Vacation(models.Model):
    name=models.CharField(max_length=100)
    country = models.ForeignKey(Country, on_delete=models.CASCADE)
    activity = models.ForeignKey(Activity, on_delete=models.CASCADE)
    month = models.CharField(max_length=100)
    related_season = models.ForeignKey(Season, on_delete=models.CASCADE)
    def __str__(self):
        return self.name

