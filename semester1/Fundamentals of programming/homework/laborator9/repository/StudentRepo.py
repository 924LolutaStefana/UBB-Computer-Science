from domain.domain import *
from exceptions.RepoError import RepoError


class StudentRepo:
    def __init__(self):
        self._students = self.generate_students()

    def generate_students(self):
        return [Student(1, "Maria", 921), Student(2, "Ioana", 921), Student(3, "Stefana", 924),
                Student(4, "Andreea", 924),
                Student(5, "Vlad", 927), Student(6, "Marian", 923), Student(7, "Gigel", 927), Student(8, "Paul", 923),
                Student(9, "Bianca", 923)
            , Student(10, "Radu", 925)]

    def get_all(self):
        return self._students

    def size(self):
        return len(self._students)

    def add_student(self, student):
        """
        Function that adds a student to the list of students
        :param student: Student
        :return:
        """
        id = student.get_id()
        for student1 in self._students:
            if id == student1.get_id():
                raise RepoError("Id should be unique!")
        self._students.append(student)

    def delete_student(self, student):
        """
        Function that deletes a student from the list of students
        :param student: Student
        :return:
        """
        if len(self._students) == 0:
            raise RepoError("There is no student to remove!")
        id = student.get_id()
        if id  in self._students:
            raise RepoError("This student doesn't exist!")



        self._students.remove(student)


    def update_student(self, id, new_name, new_group):
        """
        Function that updates a student
        :param id: int
        :param new_name:string
        :param new_group: int
        :return:
        """

        if id  in self._students:
            raise RepoError("This student doesn't exist!")
        for student in self._students:
            if id == student.get_id():
                student.set_name(new_name)
                student.set_group(new_group)
                break
