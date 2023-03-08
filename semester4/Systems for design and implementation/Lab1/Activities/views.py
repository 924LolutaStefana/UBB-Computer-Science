from django.shortcuts import render
from rest_framework import generics
from Activities.models import Activity
from Activities.serializers import ActivitySerializer
# Create your views here.
class ActivityList(generics.ListCreateAPIView):
    queryset = Activity.objects.all()
    serializer_class = ActivitySerializer
class ActivityDetail(generics.RetrieveUpdateDestroyAPIView):
    queryset = Activity
    serializer_class = ActivitySerializer
