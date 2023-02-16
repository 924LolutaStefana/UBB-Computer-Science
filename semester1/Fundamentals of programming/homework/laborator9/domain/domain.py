class Student:
    def __init__(self,id,name,group):
        self.__student_id=id
        self.__name=name
        self.__group=group
    def get_id(self):
        return self.__student_id
    def get_name(self):
        return self.__name
    def get_group(self):
        return self.__group
    def set_id(self,id):
        self.__student_id=id
    def set_name(self,name):
        self.__name=name
    def set_group(self,group):
        self.__group=group
    def __str__(self):
        return f"Student id:{self.__student_id}, Name:{self.__name}, Group:{self.__group}"
class Assignment:
    def __init__(self,id,descr,deadline):
        self.__assignment_id=id
        self.__description=descr
        self.__deadline=deadline
    def get_id(self):
        return self.__assignment_id
    def get_description(self):
        return self.__description
    def get_deadline(self):
        return self.__deadline
    def set_id(self,id):
        self.__assignment_id=id
    def set_description(self,descr):
        self.__description=descr
    def set_deadline(self,dead):
        self.__deadline=dead
    def __str__(self):
        return f"Assignment id:{self.__assignment_id}, Description:{self.__description}, Deadline:{self.__deadline}"
class Grade:
    def __init__(self,ass_id,student_id,value):
        self.__assignment_id=ass_id
        self.__student_id=student_id
        self.__grade_value=value
    def get_assignment_id(self):
        return self.__assignment_id

    def get_student_id(self):
        return self.__student_id
    def get_grade_value(self):
        return self.__grade_value
    def set_assignment_id(self,id):
        self.__assignment_id=id

    def set_student_id(self, id):
        self.__student_id = id
    def set_grade_value(self,value):
        self.__grade_value=value
    def __str__(self):
        if self.__grade_value==0:
            return f"Assignment id:{self.__assignment_id}, Student id:{self.__student_id}, Grade:ungraded"
        return f"Assignment id:{self.__assignment_id}, Student id:{self.__student_id}, Grade:{self.__grade_value}"
