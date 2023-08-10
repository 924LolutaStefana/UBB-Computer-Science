using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Data.SqlClient;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;


namespace Laborator1Db
{
    public partial class Form1 : Form

    {
        private SqlConnection dbConnection;
        private SqlDataAdapter daDepartaments;
        private SqlDataAdapter daManagers;
        private DataSet tableData;
        private DataRelation drDepartamentManager;
        BindingSource bsDepartaments;
        BindingSource bsManagers;

        public Form1()
        {
            InitializeComponent();
        }
        private void ReloadManagersTableView()
        {
            if (tableData.Tables["Manager"] != null)
            {
                tableData.Tables["Manager"].Clear();
            }
            daManagers.Fill (tableData, "Manager");
            ManagerView.DataSource = bsManagers;
        }
        private void DepartamentView_SelectionChanged(object sender, EventArgs e)
        {
            managerIdText.Clear();
            departamentIdText.Clear();
            nameText.Clear();
            yearText.Clear();
            
            if (DepartamentView.SelectedRows.Count != 0)
            {
                DataGridViewRow selectedRow = DepartamentView.SelectedRows[0];
                daManagers.SelectCommand = new SqlCommand("SELECT * FROM Manager where departament_id = " + selectedRow.Cells[0].Value, dbConnection);
                ReloadManagersTableView();
            }

        }
        private void ManagerView_SelectionChanged(object sender, EventArgs e)
        {
            if (ManagerView.SelectedRows.Count != 0)
            {
                DataGridViewRow selectedRow = ManagerView.SelectedRows[0];
                managerIdText.Text = selectedRow.Cells[0].Value.ToString();
                nameText.Text = selectedRow.Cells[1].Value.ToString();
                departamentIdText.Text = selectedRow.Cells[3].Value.ToString();
                yearText.Text = selectedRow.Cells[2].Value.ToString();
                
            }
        }

        private void ManagerView_DataError(object sender, DataGridViewDataErrorEventArgs e)
        {
            if (e.Exception is InvalidConstraintException)
                MessageBox.Show("The manager id you provided does not exist!");
            else if (e.Exception is FormatException)
                MessageBox.Show(e.Exception.Message);
            else
                try
                {
                    throw e.Exception;
                }
                catch (Exception ex)
                {
                    MessageBox.Show(ex.ToString());
                }
        }


        private void addButton_Click(object sender, EventArgs e)
        {
            SqlCommand command = new SqlCommand("INSERT INTO Manager ( manager_name, year_of_birth, departament_id) " +
                "VALUES ( @ManagerName, @Year, @DepartamentId)", dbConnection);
            if (managerIdText.Text.Length != 0)
            {
                try
                {
                    int manager_id = Int32.Parse(managerIdText.Text);
                    if (departamentIdText.Text.Length != 0)
                    {
                        int departament_id = Int32.Parse(departamentIdText.Text);
                       
                        int year;
                        if (yearText.Text.Length != 0)
                            year = Int32.Parse(yearText.Text);
                        else
                            year = 1900;
                        command.Parameters.Add("@ManagerId", SqlDbType.Int);
                        command.Parameters["@ManagerId"].Value = manager_id;

                        command.Parameters.Add("@ManagerName", SqlDbType.VarChar, 50);
                        command.Parameters["@ManagerName"].Value = nameText.Text;


                        command.Parameters.Add("@Year", SqlDbType.Int);
                        command.Parameters["@Year"].Value = year;

                        command.Parameters.Add("@DepartamentId", SqlDbType.Int);
                        command.Parameters["@DepartamentId"].Value = departament_id;


                       

                        try
                        {
                            daManagers.InsertCommand = command;
                            daManagers.InsertCommand.ExecuteNonQuery();
                            ReloadManagersTableView();
                        }
                        catch (SqlException sqlException)
                        {
                            if (sqlException.Number == 2627)
                                MessageBox.Show("The manager id must be unique!");
                            else if (sqlException.Number == 547)
                                MessageBox.Show("There's no departament with the given id!");
                            else
                                MessageBox.Show(sqlException.Message);
                        }
                    }
                    else
                        MessageBox.Show("Please provide a departament id!");
                }
                catch (FormatException ex)
                {
                    MessageBox.Show(ex.Message);
                }
            }
            else
                MessageBox.Show("Please provide a manager id!");
        }

        private void removeButton_Click(object sender, EventArgs e)
        {
            SqlCommand command = new SqlCommand("DELETE FROM Manager WHERE manager_id = @ManagerID", dbConnection);
           
                int manager_id = Int32.Parse(managerIdText.Text);
                command.Parameters.Add("@ManagerID", SqlDbType.Int);
                command.Parameters["@ManagerID"].Value = manager_id;
                try
                {
                    daManagers.DeleteCommand = command;
                    int numberOfDeletedManagers = daManagers.DeleteCommand.ExecuteNonQuery();
                    if (numberOfDeletedManagers == 0)
                    {
                        MessageBox.Show("There is no manager with the given id!");
                    }
                    else
                   

                    {
                        ReloadManagersTableView();
                    }
                }
                catch (SqlException sqlException)
                {
                    MessageBox.Show(sqlException.ToString());
                }
            

        }

        private void updateButton_Click(object sender, EventArgs e)
        {

            SqlCommand command = new SqlCommand("UPDATE Manager " +
               "SET manager_name = @ManagerName,year_of_birth = @Year , " +
               "departament_id = @DepartamentId " +
               "WHERE manager_id = @ManagerID", dbConnection);
            int manager_id = Int32.Parse(managerIdText.Text);
            int departament_id = Int32.Parse(departamentIdText.Text);
            int year = Int32.Parse(yearText.Text);
           
            command.Parameters.Add("@ManagerID", SqlDbType.Int);
            command.Parameters["@ManagerID"].Value = manager_id;

            command.Parameters.Add("@ManagerName", SqlDbType.VarChar, 50);
            command.Parameters["@ManagerName"].Value = nameText.Text;

            command.Parameters.Add("@Year", SqlDbType.Int);
            command.Parameters["@Year"].Value = year;

            command.Parameters.Add("@DepartamentId", SqlDbType.Int);
            command.Parameters["@DepartamentId"].Value = departament_id;

           

           
            try
            {
                daManagers.UpdateCommand = command;
                int numberOfUpdatedSingers = daManagers.UpdateCommand.ExecuteNonQuery();
                if (numberOfUpdatedSingers != 0)
                {
                    ReloadManagersTableView();
                }
                else
                {
                    MessageBox.Show("There is no manager with the given id!");
                }
            }
            catch (SqlException sqlException)
            {
                if (sqlException.Number == 2627)
                    MessageBox.Show("The manager id must be unique!");
                else if (sqlException.Number == 547)
                    MessageBox.Show("There's no departament with the given id!");
                else
                    MessageBox.Show(sqlException.Message);
            }
           
        }

            private void Form1_Load(object sender, EventArgs e)
        {
            dbConnection = new SqlConnection("Server=LAPTOP-D1KN43HE;Database=laborator2;Trusted_Connection=true");
            dbConnection.Open();

            daDepartaments = new SqlDataAdapter("SELECT * FROM Departament", dbConnection);
            tableData = new DataSet();
            daDepartaments.Fill(tableData, "Departament");
            DepartamentView.SelectionMode = DataGridViewSelectionMode.FullRowSelect;

            daManagers = new SqlDataAdapter("SELECT * FROM Manager", dbConnection);
            daManagers.Fill(tableData, "Manager");
            ManagerView.SelectionMode = DataGridViewSelectionMode.FullRowSelect;

            DataColumn departamentIdDepartament = tableData.Tables["Departament"].Columns["departament_id"];
            DataColumn departamentIdManager = tableData.Tables["Manager"].Columns["departament_id"];
            drDepartamentManager = new DataRelation("FK_DEPARTAMENT_MANAGER", departamentIdDepartament, departamentIdManager);
            tableData.Relations.Add(drDepartamentManager);

            bsDepartaments = new BindingSource();
            bsDepartaments.DataSource = tableData;
            bsDepartaments.DataMember = "Departament";

            bsManagers = new BindingSource();
            bsManagers.DataSource = bsDepartaments;
            bsManagers.DataMember = "FK_DEPARTAMENT_MANAGER";
            DepartamentView.DataSource = bsDepartaments;
        }

        private void ManagerLabel_Click(object sender, EventArgs e)
        {

        }
    }
}