from django.shortcuts import render
from rest_framework import generics
from Activities.models import *
from Activities.serializers import *
# Create your views here.
from rest_framework.views import APIView
from django.db.models import Avg

from rest_framework.response import Response
class SeasonList(generics.ListCreateAPIView):
    queryset = Season.objects.all()
    serializer_class = SeasonSerializer
class SeasonDetail(generics.RetrieveUpdateDestroyAPIView):
    queryset = Season
    serializer_class = SeasonSerializer
class ActivityList(generics.ListCreateAPIView):
    queryset = Activity.objects.all()
    serializer_class = ActivitySerializer
class ActivityDetail(generics.RetrieveUpdateDestroyAPIView):
    queryset = Activity
    serializer_class = ActivitySerializer1

class CountryList(generics.ListCreateAPIView):
    queryset = Country.objects.all()
    serializer_class = CountrySerializer
class CountryDetail(generics.RetrieveUpdateDestroyAPIView):
    queryset = Country
    serializer_class = CountrySerializer
class SeasonsGetGradeHigherThan(APIView):
    def get(self, request):
        grade = 8  # replace with your desired value
        queryset = Season.objects.filter(a_grade__gt=grade)
        serializer = SeasonSerializer(queryset, many=True)
        return Response(serializer.data)

class SeasonDetail1(generics.RetrieveUpdateDestroyAPIView):
    queryset = Season
    serializer_class = ParentSerializer


class VacationList(generics.ListCreateAPIView):
    queryset = Vacation.objects.all()
    serializer_class = VacationSerializer
class VacationDetail(generics.RetrieveUpdateDestroyAPIView):
    queryset = Vacation.objects.all()
    serializer_class=VacationSerializer
class CountryActivityAvgMoneyView(APIView):
    def get(self, request):

        countries= Country.objects.annotate(avg_money=Avg('activities__money_required')).order_by('avg_money')
        # Serialize the data using a DTO class
        dto_list = []
        for country in countries:
            dto = CountryActivityAvgMoneyDTO(country.id, country.name, country.avg_money)
            dto_list.append(dto)
        serializer = CountryActivityAvgMoneySerializer(dto_list, many=True)
        return Response(serializer.data)
class SeasonVacationAvgGradeView(APIView):
    def get(self, request):

        vacations= Vacation.objects.annotate(avg_grade=Avg('related_season__a_grade')).order_by('avg_grade')
        # Serialize the data using a DTO class
        dto_list = []
        for vacation in vacations:
            dto = SeasonVacationAvgGradeDTO(vacation.id, vacation.name, vacation.avg_grade)
            dto_list.append(dto)
        serializer = SeasonVacationAvgGradeSerializer(dto_list, many=True)
        return Response(serializer.data)