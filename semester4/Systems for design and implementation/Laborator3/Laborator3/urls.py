"""Laborator2 URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/4.1/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  path('', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  path('', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.urls import include, path
    2. Add a URL to urlpatterns:  path('blog/', include('blog.urls'))
"""
from django.contrib import admin
from django.urls import path
from Activities.views import *

urlpatterns = [
    path('admin/', admin.site.urls),
    path('activities/', ActivityList.as_view()),
    path('activities/<int:pk>', ActivityDetail.as_view()),

    path('seasons/', SeasonList.as_view()),
    path('seasons/<int:pk>', SeasonDetail1.as_view()),
    path('countries/',  CountryList.as_view()),
    path('countries/<int:pk>', CountryDetail.as_view()),
    path('seasons/grade', SeasonsGetGradeHigherThan.as_view()),
    path("vacation/", VacationList.as_view()),
    path("vacation/<int:pk>", VacationDetail.as_view()),
    path("countries_avg/", CountryActivityAvgMoneyView.as_view()),
    path("vacations_avg/",SeasonVacationAvgGradeView.as_view()),

]
