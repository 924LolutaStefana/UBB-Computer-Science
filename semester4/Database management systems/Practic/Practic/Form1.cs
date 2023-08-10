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

namespace Practic
{
    public partial class Form1 : Form
    {
        SqlConnection conn = new SqlConnection("Data Source=LAPTOP-D1KN43HE;Initial Catalog=practicsem2;Integrated Security=true");
        DataSet ds;
        SqlDataAdapter daStores;
        SqlDataAdapter daClients;
        BindingSource bsStores;
        BindingSource bsClients;
        SqlCommandBuilder cmd;


        public Form1()
        {
            InitializeComponent();
        }

        private void buttonConnect_Click(object sender, EventArgs e)
        {
            ds = new DataSet();
            daStores = new SqlDataAdapter("SELECT * from Stores", conn);
            daClients = new SqlDataAdapter("SELECT * from Clients", conn);
            daStores.Fill(ds, "Stores");
            daClients.Fill(ds, "Clients");
            DataRelation dr = new DataRelation("FK_Stores_Clients", ds.Tables["Stores"].Columns["storeId"],
                ds.Tables["Clients"].Columns["storeId"]);
            ds.Relations.Add(dr);

            cmd = new SqlCommandBuilder(daClients);

            bsClients = new BindingSource();
            bsStores = new BindingSource();
            bsStores.DataSource = ds;
            bsStores.DataMember = "Stores";
            bsClients.DataSource = bsStores;
            bsClients.DataMember = "FK_Stores_Clients";

            dgvStores.DataSource = bsStores;
            dgvClients.DataSource = bsClients;


        }

        private void buttonSave_Click(object sender, EventArgs e)
        {
            daClients.Update(ds, "Clients");
        }
    }
}
