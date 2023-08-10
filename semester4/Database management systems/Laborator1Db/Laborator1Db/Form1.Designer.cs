namespace Laborator1Db
{
    partial class Form1
    {
        /// <summary>
        ///  Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        ///  Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        ///  Required method for Designer support - do not modify
        ///  the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.DepartamentView = new System.Windows.Forms.DataGridView();
            this.ManagerView = new System.Windows.Forms.DataGridView();
            this.DepartamentLabel = new System.Windows.Forms.Label();
            this.ManagerLabel = new System.Windows.Forms.Label();
            this.ManagerIdLabel = new System.Windows.Forms.Label();
            this.DepartamentIdLabel = new System.Windows.Forms.Label();
            this.NameLabel = new System.Windows.Forms.Label();
            this.YearLabel = new System.Windows.Forms.Label();
            this.managerIdText = new System.Windows.Forms.TextBox();
            this.nameText = new System.Windows.Forms.TextBox();
            this.yearText = new System.Windows.Forms.TextBox();
            this.departamentIdText = new System.Windows.Forms.TextBox();
            this.addButton = new System.Windows.Forms.Button();
            this.removeButton = new System.Windows.Forms.Button();
            this.updateButton = new System.Windows.Forms.Button();
            ((System.ComponentModel.ISupportInitialize)(this.DepartamentView)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.ManagerView)).BeginInit();
            this.SuspendLayout();
            // 
            // DepartamentView
            // 
            this.DepartamentView.AllowUserToAddRows = false;
            this.DepartamentView.AllowUserToDeleteRows = false;
            this.DepartamentView.BackgroundColor = System.Drawing.Color.Azure;
            this.DepartamentView.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.DepartamentView.Location = new System.Drawing.Point(13, 37);
            this.DepartamentView.Margin = new System.Windows.Forms.Padding(2);
            this.DepartamentView.Name = "DepartamentView";
            this.DepartamentView.ReadOnly = true;
            this.DepartamentView.RowHeadersWidth = 102;
            this.DepartamentView.RowTemplate.Height = 40;
            this.DepartamentView.Size = new System.Drawing.Size(502, 273);
            this.DepartamentView.TabIndex = 0;
            this.DepartamentView.SelectionChanged += new System.EventHandler(this.DepartamentView_SelectionChanged);
            // 
            // ManagerView
            // 
            this.ManagerView.AccessibleName = "ManagerView";
            this.ManagerView.AllowUserToAddRows = false;
            this.ManagerView.AllowUserToDeleteRows = false;
            this.ManagerView.BackgroundColor = System.Drawing.Color.Azure;
            this.ManagerView.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.ManagerView.Location = new System.Drawing.Point(519, 37);
            this.ManagerView.Margin = new System.Windows.Forms.Padding(2);
            this.ManagerView.Name = "ManagerView";
            this.ManagerView.RowHeadersWidth = 102;
            this.ManagerView.RowTemplate.Height = 40;
            this.ManagerView.Size = new System.Drawing.Size(672, 273);
            this.ManagerView.TabIndex = 1;
            this.ManagerView.DataError += new System.Windows.Forms.DataGridViewDataErrorEventHandler(this.ManagerView_DataError);
            this.ManagerView.SelectionChanged += new System.EventHandler(this.ManagerView_SelectionChanged);
            // 
            // DepartamentLabel
            // 
            this.DepartamentLabel.AccessibleName = "DepartamentLabel";
            this.DepartamentLabel.AutoSize = true;
            this.DepartamentLabel.Font = new System.Drawing.Font("Segoe UI", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point);
            this.DepartamentLabel.Location = new System.Drawing.Point(177, 3);
            this.DepartamentLabel.Margin = new System.Windows.Forms.Padding(2, 0, 2, 0);
            this.DepartamentLabel.Name = "DepartamentLabel";
            this.DepartamentLabel.Size = new System.Drawing.Size(176, 32);
            this.DepartamentLabel.TabIndex = 2;
            this.DepartamentLabel.Text = "Departaments";
            // 
            // ManagerLabel
            // 
            this.ManagerLabel.AutoSize = true;
            this.ManagerLabel.Font = new System.Drawing.Font("Segoe UI", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point);
            this.ManagerLabel.Location = new System.Drawing.Point(669, 3);
            this.ManagerLabel.Margin = new System.Windows.Forms.Padding(2, 0, 2, 0);
            this.ManagerLabel.Name = "ManagerLabel";
            this.ManagerLabel.Size = new System.Drawing.Size(127, 32);
            this.ManagerLabel.TabIndex = 3;
            this.ManagerLabel.Text = "Managers";
            this.ManagerLabel.Click += new System.EventHandler(this.ManagerLabel_Click);
            // 
            // ManagerIdLabel
            // 
            this.ManagerIdLabel.AutoSize = true;
            this.ManagerIdLabel.Font = new System.Drawing.Font("Segoe UI", 11F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point);
            this.ManagerIdLabel.Location = new System.Drawing.Point(601, 339);
            this.ManagerIdLabel.Margin = new System.Windows.Forms.Padding(2, 0, 2, 0);
            this.ManagerIdLabel.Name = "ManagerIdLabel";
            this.ManagerIdLabel.Size = new System.Drawing.Size(135, 30);
            this.ManagerIdLabel.TabIndex = 4;
            this.ManagerIdLabel.Text = "Manager ID";
            // 
            // DepartamentIdLabel
            // 
            this.DepartamentIdLabel.AutoSize = true;
            this.DepartamentIdLabel.Font = new System.Drawing.Font("Segoe UI", 11F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point);
            this.DepartamentIdLabel.Location = new System.Drawing.Point(0, 339);
            this.DepartamentIdLabel.Margin = new System.Windows.Forms.Padding(2, 0, 2, 0);
            this.DepartamentIdLabel.Name = "DepartamentIdLabel";
            this.DepartamentIdLabel.Size = new System.Drawing.Size(181, 30);
            this.DepartamentIdLabel.TabIndex = 5;
            this.DepartamentIdLabel.Text = "Departament ID";
            // 
            // NameLabel
            // 
            this.NameLabel.AutoSize = true;
            this.NameLabel.Font = new System.Drawing.Font("Segoe UI", 11F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point);
            this.NameLabel.Location = new System.Drawing.Point(601, 389);
            this.NameLabel.Margin = new System.Windows.Forms.Padding(2, 0, 2, 0);
            this.NameLabel.Name = "NameLabel";
            this.NameLabel.Size = new System.Drawing.Size(74, 30);
            this.NameLabel.TabIndex = 6;
            this.NameLabel.Text = "Name";
            // 
            // YearLabel
            // 
            this.YearLabel.AutoSize = true;
            this.YearLabel.Font = new System.Drawing.Font("Segoe UI", 11F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point);
            this.YearLabel.Location = new System.Drawing.Point(601, 443);
            this.YearLabel.Margin = new System.Windows.Forms.Padding(2, 0, 2, 0);
            this.YearLabel.Name = "YearLabel";
            this.YearLabel.Size = new System.Drawing.Size(142, 30);
            this.YearLabel.TabIndex = 7;
            this.YearLabel.Text = "Year of birth";
            // 
            // managerIdText
            // 
            this.managerIdText.Location = new System.Drawing.Point(757, 336);
            this.managerIdText.Margin = new System.Windows.Forms.Padding(2);
            this.managerIdText.Name = "managerIdText";
            this.managerIdText.Size = new System.Drawing.Size(228, 31);
            this.managerIdText.TabIndex = 10;
            // 
            // nameText
            // 
            this.nameText.Location = new System.Drawing.Point(757, 383);
            this.nameText.Margin = new System.Windows.Forms.Padding(2);
            this.nameText.Name = "nameText";
            this.nameText.Size = new System.Drawing.Size(227, 31);
            this.nameText.TabIndex = 11;
            // 
            // yearText
            // 
            this.yearText.Location = new System.Drawing.Point(756, 440);
            this.yearText.Margin = new System.Windows.Forms.Padding(2);
            this.yearText.Name = "yearText";
            this.yearText.Size = new System.Drawing.Size(228, 31);
            this.yearText.TabIndex = 12;
            // 
            // departamentIdText
            // 
            this.departamentIdText.Location = new System.Drawing.Point(177, 339);
            this.departamentIdText.Margin = new System.Windows.Forms.Padding(2);
            this.departamentIdText.Name = "departamentIdText";
            this.departamentIdText.Size = new System.Drawing.Size(361, 31);
            this.departamentIdText.TabIndex = 15;
            // 
            // addButton
            // 
            this.addButton.BackColor = System.Drawing.Color.CadetBlue;
            this.addButton.Font = new System.Drawing.Font("Segoe UI", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point);
            this.addButton.Location = new System.Drawing.Point(22, 505);
            this.addButton.Margin = new System.Windows.Forms.Padding(2);
            this.addButton.Name = "addButton";
            this.addButton.Size = new System.Drawing.Size(226, 97);
            this.addButton.TabIndex = 16;
            this.addButton.Text = "Add Manager";
            this.addButton.UseVisualStyleBackColor = false;
            this.addButton.Click += new System.EventHandler(this.addButton_Click);
            // 
            // removeButton
            // 
            this.removeButton.BackColor = System.Drawing.Color.CadetBlue;
            this.removeButton.Font = new System.Drawing.Font("Segoe UI", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point);
            this.removeButton.Location = new System.Drawing.Point(296, 505);
            this.removeButton.Margin = new System.Windows.Forms.Padding(2);
            this.removeButton.Name = "removeButton";
            this.removeButton.Size = new System.Drawing.Size(228, 97);
            this.removeButton.TabIndex = 17;
            this.removeButton.Text = "Remove Manager";
            this.removeButton.UseVisualStyleBackColor = false;
            this.removeButton.Click += new System.EventHandler(this.removeButton_Click);
            // 
            // updateButton
            // 
            this.updateButton.BackColor = System.Drawing.Color.CadetBlue;
            this.updateButton.Font = new System.Drawing.Font("Segoe UI", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point);
            this.updateButton.ForeColor = System.Drawing.SystemColors.Desktop;
            this.updateButton.Location = new System.Drawing.Point(590, 505);
            this.updateButton.Margin = new System.Windows.Forms.Padding(2);
            this.updateButton.Name = "updateButton";
            this.updateButton.Size = new System.Drawing.Size(239, 97);
            this.updateButton.TabIndex = 18;
            this.updateButton.Text = "Update Manager";
            this.updateButton.UseVisualStyleBackColor = false;
            this.updateButton.Click += new System.EventHandler(this.updateButton_Click);
            // 
            // Form1
            // 
            this.AccessibleName = "";
            this.AutoScaleDimensions = new System.Drawing.SizeF(10F, 25F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1202, 821);
            this.Controls.Add(this.updateButton);
            this.Controls.Add(this.removeButton);
            this.Controls.Add(this.addButton);
            this.Controls.Add(this.departamentIdText);
            this.Controls.Add(this.yearText);
            this.Controls.Add(this.nameText);
            this.Controls.Add(this.managerIdText);
            this.Controls.Add(this.YearLabel);
            this.Controls.Add(this.NameLabel);
            this.Controls.Add(this.DepartamentIdLabel);
            this.Controls.Add(this.ManagerIdLabel);
            this.Controls.Add(this.ManagerLabel);
            this.Controls.Add(this.DepartamentLabel);
            this.Controls.Add(this.ManagerView);
            this.Controls.Add(this.DepartamentView);
            this.Margin = new System.Windows.Forms.Padding(2);
            this.Name = "Form1";
            this.Text = "Form1";
            this.Load += new System.EventHandler(this.Form1_Load);
            ((System.ComponentModel.ISupportInitialize)(this.DepartamentView)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.ManagerView)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.DataGridView DepartamentView;
        private System.Windows.Forms.DataGridView ManagerView;
        private System.Windows.Forms.Label DepartamentLabel;
        private System.Windows.Forms.Label ManagerLabel;
        private System.Windows.Forms.Label ManagerIdLabel;
        private System.Windows.Forms.Label DepartamentIdLabel;
        private System.Windows.Forms.Label NameLabel;
        private System.Windows.Forms.Label YearLabel;
        private System.Windows.Forms.Label CoachLabel;
        private System.Windows.Forms.Label StaffLabel;
        private System.Windows.Forms.TextBox managerIdText;
        private System.Windows.Forms.TextBox nameText;
        private System.Windows.Forms.TextBox yearText;
        private System.Windows.Forms.TextBox coachText;
        private System.Windows.Forms.TextBox staffText;
        private System.Windows.Forms.TextBox departamentIdText;
        private System.Windows.Forms.Button addButton;
        private System.Windows.Forms.Button removeButton;
        private System.Windows.Forms.Button updateButton;
    }
}