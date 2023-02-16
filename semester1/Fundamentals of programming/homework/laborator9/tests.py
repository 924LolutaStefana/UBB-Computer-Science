import unittest

from exceptions.RepoError import RepoError
from service.service import *


class Tests(unittest.TestCase):
    def test_add_student(self):
        service = Service()
        service._student_repo._students=[]
        service.add_student_service(1,"LALA",924)
        self.assertEqual(len(service.get_students()), 1)
        try:
            service.add_student_service(1,"LALA",924)
            assert False

        except RepoError as ve:
            assert str(ve) == "Id should be unique!"




    def test_add_assignment(self):
        service = Service()
        service._assignment_repo = []
        service.add_assignment_service(1,"gd","evr")

        self.assertEqual(len(service.get_assignments()), 1)
        try:
            service.add_assignment_service(1, "LALA", 924)
            assert False

        except RepoError as ve:
            assert str(ve) =="Id should be unique!"

    def test_delete_student(self):
        service = Service()
        service._student_repo._students = []
        try:
            service.remove_student_service(1)

        except RepoError as ve:
            assert str(ve) == "There is no student to remove!"
        service.add_student_service(1, "LALA", 924)
        try:
            service.remove_student_service(2)

        except RepoError as ve:
            assert str(ve) == "This student doesn't exist!"
        service.remove_student_service(1)
        self.assertEqual(len(service.get_students()), 0)

    def test_delete_assignment(self):
        service = Service()
        service._assignment_repo = []
        try:
            service.remove_assignment_service(1)

        except RepoError as ve:
            assert str(ve) == "There is no assignment to remove!"

    def test_students_that_are_late_handing_assignment(self):
       repo_student=StudentRepo()
       repo_assignment=AssignmentRepo()
       repo_grade=GradeRepo()
       service=Service(repo_student,repo_assignment,repo_grade)

       service.assign_student_service(1, 1)
       service.assign_student_service(1, 2)
       service.assign_student_service(1, 3)
       service.assign_student_service(2, 1)
       service.assign_student_service(2, 2)
       list=service.statistics_2("10.10.2012")

       self.assertEqual(len(list), 5)
       service.update_grade_service(1,1,10)
       list = service.statistics_2("10.10.2012")
       self.assertEqual(len(list), 4)


    def test_all(self):
        self.test_add_student()
        self.test_delete_assignment()
        self.test_delete_student()
        self.test_add_assignment()