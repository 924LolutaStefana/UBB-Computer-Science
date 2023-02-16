from exceptions.RepoError import RepoError


class GradeRepo:
    def __init__(self):
        self._grades = []

    def get_all(self):
        return self._grades

    def size(self):
        return len(self._grades)
    def add_grade(self,grade):
        """
        Function that adds a  grade to the list of grades
        :param grade: Grade
        :return:
        """
        id_student=grade.get_student_id()
        id_assignment=grade.get_assignment_id()

        for grade1 in self._grades:
            if id_student==grade1.get_student_id() and id_assignment==grade1.get_assignment_id():
                raise RepoError("This student already has that assignment!")

        self._grades.append(grade)

    def delete_grade(self, grade):
        """
        Function that deletes a grade from the list of grades
        :param grade: Grade
        :return:
        """
        id_student = grade.get_student_id()
        id_assignment = grade.get_assignment_id()
        if len(self._grades) == 0:
            raise RepoError("There is no grade to remove!")
        ok=0
        for grade1 in self._grades:
            if id_student == grade1.get_student_id() and id_assignment == grade1.get_assignment_id():
                ok=1
        if ok==1:
            self._grades.remove(grade)
        else:
            raise RepoError("This grade doesnt exist")
    def update_grade(self,id_student,id_ass,new_grade):
        """
        Function that updates a grade
        :param id_student: int
        :param id_ass: int
        :param new_grade: int
        :return:
        """

        # if id_student not in self._grades and id_ass not in self._grades:
        #     raise RepoError("This grade doesn't exist!")
        for grade in self._grades:
            if id_student == grade.get_student_id() and id_ass==grade.get_assignment_id():
                grade.set_grade_value(new_grade)
                break