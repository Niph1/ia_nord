const express = require('express');
const router = express.Router();

// data
let salesmen = [
  { firstname: 'Max', lastname: 'Mustermann', sid: 1 },
  { firstname: 'Maximilia', lastname: 'Musterfrau', sid: 2 },
];

// Route to create a new salesman
router.post('/', (req, res) => {
  const { firstname, lastname, sid } = req.body;
  const newSalesman = { firstname, lastname, sid };
  salesmen.push(newSalesman);
  res.status(201).json(newSalesman);
});

// Route to get a salesman by id
router.get('/:sid', (req, res) => {
  const sid = parseInt(req.params.sid, 10);
  const salesman = salesmen.find(s => s.sid === sid);
  if (salesman) {
    res.json(salesman);
  } else {
    res.status(404).json({ message: 'Salesman not found' });
  }
});

// Route to get all salesmen
router.get('/', (req, res) => {
  res.json(salesmen);
});

// Route to delete a salesman and their performance records
router.delete('/:sid', (req, res) => {
  const sid = parseInt(req.params.sid, 10);
  const index = salesmen.findIndex(s => s.sid === sid);
  if (index !== -1) {
    salesmen.splice(index, 1);  // Remove the salesman
    res.status(200).json({ message: 'Salesman deleted' });
  } else {
    res.status(404).json({ message: 'Salesman not found' });
  }
});

module.exports = router;
