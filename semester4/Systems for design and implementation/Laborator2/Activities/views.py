from django.shortcuts import render
from rest_framework import generics
from Activities.models import *
from Activities.serializers import *
# Create your views here.
from rest_framework.views import APIView

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