from rest_framework import serializers
from.models import *
from .dtos import *
class SeasonSerializer(serializers.ModelSerializer):
    class Meta:
        model = Season
        fields = '__all__'

class CountrySerializer(serializers.ModelSerializer):
    class Meta:
        model = Country
        fields = '__all__'
class ActivitySerializer(serializers.ModelSerializer):

    name = serializers.CharField(max_length=255)

    a_person_to_complete_the_activity_with = serializers.CharField(max_length=255)
    money_required = serializers.CharField(max_length=255)
    time_required =serializers.CharField(max_length=255)

    class Meta:
        model = Activity
        fields='__all__'

class ActivitySerializer1(serializers.ModelSerializer):


    name = serializers.CharField(max_length=255)

    a_person_to_complete_the_activity_with = serializers.CharField(max_length=255)
    money_required = serializers.CharField(max_length=255)
    time_required = serializers.CharField(max_length=255)

    related_season = SeasonSerializer(read_only=True)


    def validate_season_id(self, value):
        filter = Season.objects.filter(id=value)
        if not filter.exists():
            raise serializers.ValidationError("Season does not exist")
        return value

    class Meta:
        model = Activity
        fields = ('id', 'name', 'related_season', 'a_person_to_complete_the_activity_with', 'money_required' ,'time_required')
class ChildSerializer(serializers.HyperlinkedModelSerializer):

    related_season_id = serializers.PrimaryKeyRelatedField(queryset=Season.objects.all(),source='related_season.id')

    class Meta:
        model = Activity
        fields = ('id', 'name', 'related_season_id', 'a_person_to_complete_the_activity_with', 'money_required' ,'time_required')

    def create(self, validated_data):
        subject = Activity.objects.create(related_season=validated_data['related_season']['id'], name=validated_data['name'])

        return subject

class ParentSerializer(serializers.HyperlinkedModelSerializer):
    children = ChildSerializer(many=True, read_only=True)
    class Meta:
        model = Season
        fields = ('id','name','related_color','someone_born_on_this_month','a_grade','an_animal_that_likes_this_season','children')
class VacationSerializer(serializers.ModelSerializer):
    name = serializers.CharField(max_length=100)

    month = serializers.CharField(max_length=100)

    def validate_country_id(self, value):
        filter = Country.objects.filter(id=value)
        if not filter.exists():
            raise serializers.ValidationError("Country does not exist")
        return value

    def validate_acivity_id(self, value):
        filter = Activity.objects.filter(id=value)
        if not filter.exists():
            raise serializers.ValidationError("Activity does not exist")
        return value

    class Meta:
        model = Vacation
        fields='__all__'

class CountryActivityAvgMoneySerializer(serializers.Serializer):
    #Class for  showing all countries ordered by the average money of their activities
    country_id = serializers.IntegerField()
    country_name = serializers.CharField(max_length=255)
    avg_money = serializers.FloatField()

    def to_representation(self, instance):
        if isinstance(instance, CountryActivityAvgMoneyDTO):
            # If the serializer is given a SeasonActivityAvgMoneyDTO, return the DTO's data
            return {
                'country_id': instance.country_id,
                'country_name': instance.country_name,
                'avg_money': instance.avg_money
            }
        else:
            # Otherwise, call the parent implementation
            return super().to_representation(instance)
class SeasonVacationAvgGradeSerializer(serializers.Serializer):
    #Class for  showing all countries ordered by the average money of their activities
    vacation_id = serializers.IntegerField()
    vacation_name = serializers.CharField(max_length=255)
    avg_grade = serializers.FloatField()

    def to_representation(self, instance):
        if isinstance(instance, SeasonVacationAvgGradeSerializer ):
            # If the serializer is given a SeasonActivityAvgMoneyDTO, return the DTO's data
            return {
                'vacation_id': instance.vacation_id,
                'vacation_name': instance.vacation_name,
                'avg_grade': instance.avg_grade
            }
        else:
            # Otherwise, call the parent implementation
            return super().to_representation(instance)