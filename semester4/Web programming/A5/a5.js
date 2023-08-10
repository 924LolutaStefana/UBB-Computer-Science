// Get a reference to the table element
const table = document.getElementById('my-table');

// Add a click event listener to each header cell
const headers = table.querySelectorAll('th');
headers.forEach((header, columnIndex) => {
  header.addEventListener('click', function onClick(event) {
    
    
    // Get an array of values from the current column
    const values = [];
    const rows = table.querySelectorAll('tbody tr');
    rows.forEach((row) => {
      const cell = row.querySelectorAll('td')[columnIndex];
      const value = cell.textContent.trim();
      values.push(value);
    });

    // Sort the values in ascending or descending order
    const isAscending = header.classList.toggle('ascending');
    header.classList.toggle('descending', !isAscending);
    const sortedValues = values.sort((a, b) => {
      return isAscending ? a.localeCompare(b) : b.localeCompare(a);
    });
    isAscending ?  event.target.style.color = 'green' : event.target.style.color = 'red';
    
    // Replace the content of the table rows with the sorted values
    rows.forEach((row, rowIndex) => {
    const cell = row.querySelectorAll('td')[columnIndex];
    cell.textContent = sortedValues[rowIndex];
    
    
    });

    // Remove arrow icons from other headers
    headers.forEach((otherHeader, otherIndex) => {
    if (otherIndex !== columnIndex) {
      otherHeader.classList.remove('ascending', 'descending');
      otherHeader.style.color = 'black';
      
    }
    });
  });
});
 