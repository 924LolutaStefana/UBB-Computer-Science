from django.db import models

# Create your models here.
class Activity(models.Model):
    name=models.CharField(max_length=100)
    related_season=models.CharField(max_length=100)
    a_person_to_complete_the_activity_with=models.CharField(max_length=100)
    money_required=models.CharField(max_length=100)
    time_required=models.CharField(max_length=100)
    def __str__(self):
        return self.name