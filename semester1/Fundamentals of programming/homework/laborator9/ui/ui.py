from service.service import *


class UI:
    def __init__(self, service=None):
        if service is None:
            service = Service()
        self._service = service
    def list_student(self,students):
        for student in students:
            print(student)
    def list_grade(self,grades):
        ok=0
        for grade in grades:

            print(grade)
            ok = 1
        if ok == 0:
            print("There are no grades")


    def list_ungraded_grade(self, grades):
        ok=0
        for grade in grades:
            if grade.get_grade_value()==0:
                print(grade)
                ok=1
        if ok==0:
            print("There are no ungraded students and assignments")
    def add_student_ui(self):
        try:
            id= int(input("Give the id> "))
            name= input("Give the name>  ")
            group = int(input("Give the group>  "))
            self._service.add_student_service(id,name,group)
            print("Student successfully added!")
        except Exception as e:
            print(e)

    def remove_student_ui(self):
        try:
            given_id = int(input("Give the id> "))
            self._service.remove_student_service(given_id)
            print("Student successfully removed!")
        except Exception as e:
            print(e)
    def update_student_ui(self):
        try:
            given_id = int(input("Give the id> "))
            name=input("Give the new name>")
            group=int(input("Give the new group>"))

            self._service.update_student_service(given_id,name,group)
            print("Student successfully updated!")
        except Exception as e:
            print(e)

    def assign_student_ui(self):
        try:

            student_id = int(input("Give the id of the student that you want to assign> "))
            assign_id = int(input("Give the id of the assignment> "))

            self._service.assign_student_service(student_id, assign_id)
            print("Student successfully assigned!")
        except Exception as e:
            print(e)
    def assign_group_ui(self):
        try:

            group = int(input("Give the group that you want to assign> "))
            assign_id = int(input("Give the id of the assignment> "))

            self._service.assign_group_service(group, assign_id)
            print("Group successfully assigned!")
        except Exception as e:
            print(e)

    def list_assignment(self,assignments):
        for assignment in assignments:
            print(assignment)
    def add_assignment_ui(self):
        try:
            id= int(input("Give the id> "))
            descr= input("Give the description>  ")
            deadline = input("Give the deadline>  ")
            self._service.add_assignment_service(id,descr,deadline)
            print("Assignment successfully added!")
        except Exception as e:
            print(e)
    def remove_assignment_ui(self):
        try:
            given_id = int(input("Give the id> "))
            self._service.remove_assignment_service(given_id)
            print("Assignment successfully removed!")
        except Exception as e:
            print(e)
    def update_assignment_ui(self):
        try:
            given_id = int(input("Give the id> "))
            descr=input("Give the new description>")
            deadline=input("Give the new deadline>")

            self._service.update_assignment_service(given_id,descr,deadline)
            print("Assignment successfully updated!")
        except Exception as e:
            print(e)
    def update_grade_ui(self):
        try:
            studentid = int(input("Give the id of the student> "))
            assignmentid = int(input("Give the id of the assignment> "))
            value = int(input("The grade of the student at this assignment> "))

            self._service.update_grade_service(studentid,assignmentid,value)
            print("Student successfully graded!")
        except Exception as e:
            print(e)
    def list_students_given_assignment(self):
        try:
            assignmentid = int(input("Give the id of the assignment> "))

            for entity in self._service.get_all_student_with_a_given_assignment(assignmentid):
                print(str(entity))
        except Exception as e:
            print(e)

    def list_students_past_deadline(self):
        deadline=input("Give the deadline")
        for entity in self._service.statistics_2(deadline):
            print(str(entity))

    def list_statistic_3(self):

        for entity in self._service.statistics_3():
            print(str(entity))


    @staticmethod
    def _print_menu_add():
        print("\n0. Go back to main menu")
        print("1. Add a student")
        print("2. Add an assignment")

    @staticmethod
    def _print_menu_list():
        print("\n0. Go back to main menu")
        print("1. List all the students")
        print("2. List all the assignments")



    @staticmethod
    def _print_menu_remove():
        print("\n0. Go back to main menu")
        print("1. Remove a student")
        print("2. Remove an assignment")

    @staticmethod
    def _print_menu_update():
        print("\n0. Go back to main menu")

        print("1. Update a student")
        print("2. Update an assignment")
    @staticmethod
    def _print_menu():
        print("\n0. Exit")
        print("1. Add functions")
        print("2. List functions")
        print("3. Remove functions")
        print("4. Update functions")
        print("5. Give assignments to a student or to a group of students")
        print("6. List all the grades")
        print("7. Grade students")
        print("8. All students who received a given assignment")
        print("9. All students who are late in handing in at least one assignment")
        print("10. Students with the best school situation, sorted in descending order of the average grade received for all graded assignments.")

    def start(self):

        while True:
            self._print_menu()
            userchoice = input("> ")
            if userchoice == "0":
               break
            elif userchoice == "1":


                while True:
                    self._print_menu_add()
                    choice = input("Choose> ")
                    if choice == "0":
                        break
                    elif choice == "1":
                        self.add_student_ui()
                    elif choice == "2":
                        self.add_assignment_ui()
                    else:
                        print("Invalid choice")



            elif userchoice == "2":
                while True:
                    self._print_menu_list()
                    choice = input("Choose> ")
                    if choice == "0":
                        break
                    elif choice == "1":
                        self.list_student(self._service.get_students())
                    elif choice == "2":
                        self.list_assignment(self._service.get_assignments())
                    else:
                        print("Invalid choice")

            elif userchoice == "3":
                while True:
                    self._print_menu_remove()
                    choice = input("Choose> ")
                    if choice == "0":
                        break
                    elif choice == "1":
                        self.remove_student_ui()
                    elif choice == "2":
                        self.remove_assignment_ui()
                    else:
                        print("Invalid choice")

            elif userchoice == "4":
                while True:
                    self._print_menu_update()
                    choice = input("Choose> ")
                    if choice == "0":
                        break
                    elif choice == "1":
                        self.update_student_ui()
                    elif choice == "2":
                        self.update_assignment_ui()
                    else:
                        print("Invalid choice")
            elif userchoice=="5":
                choice=input("Do you want to assign to a student or a group?")
                if choice=="student":
                    self.assign_student_ui()
                elif choice == "group":
                    self.assign_group_ui()
            elif userchoice == "6":
                self.list_grade(self._service.get_grades())
            elif userchoice=="7":
                print("The list of ungraded assignments is: ")
                self.list_ungraded_grade(self._service.get_grades())
                self.update_grade_ui()
            elif userchoice=="8":
                self.list_students_given_assignment()
            elif userchoice=="9":
                self.list_students_past_deadline()
            elif userchoice=="10":
                self.list_statistic_3()



