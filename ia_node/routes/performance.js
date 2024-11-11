const express = require('express');
const router = express.Router();

// testing data
let performanceRecords = [
  { sid: 1, year: 2023, opennessScore: 4, leadershipScore: 5, socialScore: 3, attitudeScore: 4, communicationScore: 5, integrityScore: 5 },
  { sid: 1, year: 2022, opennessScore: 3, leadershipScore: 4, socialScore: 3, attitudeScore: 4, communicationScore: 4, integrityScore: 4 },
];

// Route to add a performance record
router.post('/', (req, res) => {
  const { sid, year, opennessScore, leadershipScore, socialScore, attitudeScore, communicationScore, integrityScore } = req.body;
  const newRecord = { sid, year, opennessScore, leadershipScore, socialScore, attitudeScore, communicationScore, integrityScore };
  performanceRecords.push(newRecord);
  res.status(201).json(newRecord);
});

// Route to get all performance records for a salesman
router.get('/:sid', (req, res) => {
  const sid = parseInt(req.params.sid, 10);
  const records = performanceRecords.filter(r => r.sid === sid);
  if (records.length > 0) {
    res.json(records);
  } else {
    res.status(404).json({ message: 'No performance records found' });
  }
});

// Route to get a performance record for a salesman in a specific year
router.get('/:sid/:year', (req, res) => {
  const sid = parseInt(req.params.sid, 10);
  const year = parseInt(req.params.year, 10);
  const record = performanceRecords.find(r => r.sid === sid && r.year === year);
  if (record) {
    res.json(record);
  } else {
    res.status(404).json({ message: 'Performance record not found for this year' });
  }
});

module.exports = router;
