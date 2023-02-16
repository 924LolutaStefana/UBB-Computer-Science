from domain.domain import Assignment
from exceptions.RepoError import RepoError


class AssignmentRepo:
    def __init__(self):
        self._assignments = self.generate_assignments()

    def generate_assignments(self):
        return [Assignment(1,"math","12.12.2022"),Assignment(2,"english","21.11.2022"),Assignment(3,"geography","13.10.2022")
                ,Assignment(4,"biology","03.02.2023"),Assignment(5,"history","18.01.2023"),Assignment(6,"romanian","08.03.2023"),
                Assignment(7,"informatics","20.01.2022"),Assignment(8,"logics","19.01.2022"),Assignment(9,"sports","05.12.2022"),
                Assignment(10,"zoology","12.02.2023")]

    def get_all(self):
        return self._assignments

    def size(self):
        return len(self._assignments)

    def add_assignment(self, assignment):
        """
        Function that adds an assignment to the list of assignments
        :param assignment: Assignment
        :return:
        """
        id = assignment.get_id()
        for assignment1 in self._assignments:
            if id == assignment1.get_id():
                raise RepoError("Id should be unique!")
        self._assignments.append(assignment)

    def delete_assignment(self, assignment):
        """
        Function that deletes an assignment from the list of assignments
        :param assignment: Assignment
        :return:
        """
        if len(self._assignments) == 0:
            raise RepoError("There is no assignment to remove!")
        ok=0
        id = assignment.get_id()
        for assignment1 in self._assignments:
            if id == assignment1.get_id():
                ok=1
        if ok==1:
            self._assignments.remove(assignment)
        else:
            raise RepoError("This assignment doesnt exist")


    def update_assignment(self, id, new_desrc, new_deadline):
        """
        Updates an assignment
        :param id: int
        :param new_desrc: string
        :param new_deadline: string
        :return:
        """

        if id not in self._assignments:
            raise RepoError("This assignment doesn't exist!")
        for assignment in self._assignments:
            if id == assignment.get_id():
                assignment.set_description(new_desrc)
                assignment.set_deadline(new_deadline)
                break

