namespace Practic
{
    partial class Form1
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
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
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.dgvStores = new System.Windows.Forms.DataGridView();
            this.dgvClients = new System.Windows.Forms.DataGridView();
            this.buttonConnect = new System.Windows.Forms.Button();
            this.buttonSave = new System.Windows.Forms.Button();
            ((System.ComponentModel.ISupportInitialize)(this.dgvStores)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.dgvClients)).BeginInit();
            this.SuspendLayout();
            // 
            // dgvStores
            // 
            this.dgvStores.BackgroundColor = System.Drawing.SystemColors.ControlLight;
            this.dgvStores.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dgvStores.Location = new System.Drawing.Point(14, 15);
            this.dgvStores.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.dgvStores.Name = "dgvStores";
            this.dgvStores.RowHeadersWidth = 51;
            this.dgvStores.RowTemplate.Height = 24;
            this.dgvStores.Size = new System.Drawing.Size(824, 585);
            this.dgvStores.TabIndex = 0;
            // 
            // dgvClients
            // 
            this.dgvClients.BackgroundColor = System.Drawing.SystemColors.ControlLight;
            this.dgvClients.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dgvClients.Location = new System.Drawing.Point(890, 15);
            this.dgvClients.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.dgvClients.Name = "dgvClients";
            this.dgvClients.RowHeadersWidth = 51;
            this.dgvClients.Size = new System.Drawing.Size(873, 585);
            this.dgvClients.TabIndex = 1;
            // 
            // buttonConnect
            // 
            this.buttonConnect.BackColor = System.Drawing.SystemColors.ActiveCaption;
            this.buttonConnect.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(238)));
            this.buttonConnect.Location = new System.Drawing.Point(25, 631);
            this.buttonConnect.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.buttonConnect.Name = "buttonConnect";
            this.buttonConnect.Size = new System.Drawing.Size(204, 74);
            this.buttonConnect.TabIndex = 2;
            this.buttonConnect.Text = "Connection";
            this.buttonConnect.UseVisualStyleBackColor = false;
            this.buttonConnect.Click += new System.EventHandler(this.buttonConnect_Click);
            // 
            // buttonSave
            // 
            this.buttonSave.BackColor = System.Drawing.SystemColors.ActiveCaption;
            this.buttonSave.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(238)));
            this.buttonSave.Location = new System.Drawing.Point(277, 631);
            this.buttonSave.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.buttonSave.Name = "buttonSave";
            this.buttonSave.Size = new System.Drawing.Size(196, 74);
            this.buttonSave.TabIndex = 3;
            this.buttonSave.Text = "Save";
            this.buttonSave.UseVisualStyleBackColor = false;
            this.buttonSave.Click += new System.EventHandler(this.buttonSave_Click);
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(9F, 20F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1835, 755);
            this.Controls.Add(this.buttonSave);
            this.Controls.Add(this.buttonConnect);
            this.Controls.Add(this.dgvClients);
            this.Controls.Add(this.dgvStores);
            this.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.Name = "Form1";
            this.Text = "Form1";
            ((System.ComponentModel.ISupportInitialize)(this.dgvStores)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.dgvClients)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.DataGridView dgvStores;
        private System.Windows.Forms.DataGridView dgvClients;
        private System.Windows.Forms.Button buttonConnect;
        private System.Windows.Forms.Button buttonSave;
    }



}

