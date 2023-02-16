from domain.domain import Student, Assignment, Grade
from exceptions.ServiceError import ServiceError
from repository.AssignmentRepo import AssignmentRepo
from repository.GradeRepo import GradeRepo
from repository.StudentRepo import StudentRepo


class Service:
    def __init__(self, student_repo=None, assignment_repo=None, grade_repo=None):

        self._student_repo = student_repo
        if student_repo is None:
            self._student_repo = StudentRepo()
        self._assignment_repo = assignment_repo
        if assignment_repo is None:
            self._assignment_repo = AssignmentRepo()
        self._grade_repo = grade_repo
        if grade_repo is None:
            self._grade_repo = GradeRepo()

    def get_students(self):
        return self._student_repo.get_all()

    def add_student_service(self, id, name, group):
        student = Student(id, name, group)
        self._student_repo.add_student(student)

    def remove_student_service(self, given_id):
        for student in self.get_students():
            if given_id == student.get_id():
                self._student_repo.delete_student(student)
                for grade in self.get_grades():
                    if grade.get_student_id() == given_id:
                        self.remove_grade_service(given_id, grade.get_assignment_id())

                break

    def update_student_service(self, id, name, group):
        self._student_repo.update_student(id, name, group)

    def get_assignments(self):
        return self._assignment_repo.get_all()

    def add_assignment_service(self, id, descr, deadline):
        assignment = Assignment(id, descr, deadline)
        self._assignment_repo.add_assignment(assignment)

    def remove_assignment_service(self, given_id):
        for assignment in self.get_assignments():
            if given_id == assignment.get_id():
                self._assignment_repo.delete_assignment(assignment)
                for grade in self.get_grades():
                    if grade.get_assignment_id() == given_id:
                        self.remove_grade_service(grade.get_student_id(), given_id)
                break

    def update_assignment_service(self, id, descr, deadline):
        self._assignment_repo.update_assignment(id, descr, deadline)

    def get_grades(self):
        return self._grade_repo.get_all()

    def add_grade_service(self, student_id, assignment_id, value):
        grade = Grade(assignment_id, student_id, value)
        self._grade_repo.add_grade(grade)

    def remove_grade_service(self, student_id, assignment_id):
        for grade in self.get_grades():
            if student_id == grade.get_student_id() and assignment_id == grade.get_assignment_id():
                self._grade_repo.delete_grade(grade)
                break

    def update_grade_service(self, student_id, ass_id, grade):
        self._grade_repo.update_grade(student_id, ass_id, grade)

    def assign_student_service(self, student_id, assign_id):
        if student_id in self.get_students():
            raise ServiceError("This student doesnt exist!")
        if assign_id in self.get_assignments():
            raise ServiceError("This assignment doesnt exist!")
        self.add_grade_service(student_id, assign_id, 0)

    def assign_group_service(self, group, assign_id):
        if group in self.get_students():
            raise ServiceError("This group doesnt exist!")
        if assign_id in self.get_assignments():
            raise ServiceError("This assignment doesnt exist!")

        for student in self.get_students():
            if student.get_group() == group:
                self.add_grade_service(student.get_id(), assign_id, 0)

    def get_all_student_with_a_given_assignment(self, assign_id):
        new_list = []
        if assign_id in self.get_assignments():
            raise ServiceError("This assignment doesnt exist!")
        for grades in self.get_grades():
            if grades.get_assignment_id() == assign_id:
                new_list.append(grades)
        return new_list

    def statistics_2(self, deadline):
        new_list = []

        ungraded_list=[]
        for grades in self.get_grades():

            if grades.get_grade_value() == 0:
                ungraded_list.append(grades)
        i=0
        for assignment in self.get_assignments():
            if i ==len(ungraded_list):
                return new_list
            for grades in self.get_grades():

                if assignment.get_deadline() < deadline and grades.get_grade_value() == 0:
                    new_list.append(grades)
                    i=i+1
        return new_list
    def get_average(self,student):
        id=student.get_id()
        average = 0
        contor = 1
        for grade in self.get_grades():
            if id == grade.get_student_id():
                average = average + grade.get_grade_value()
                contor = contor + 1
        average = average // contor
        return average

    def statistics_3(self):
        list=[]
        graded_list = []
        for grades in self.get_grades():

                    graded_list.append(grades)

        i = 0


        for student in self.get_students():
            if i == len(graded_list)//2:
                #list.sort(key=self.get_average, reverse=True)
                return list

            list.append(student)
            i=i+1
        list.sort(key=self.get_average,reverse=True)
        return list


