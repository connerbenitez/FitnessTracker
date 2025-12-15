const express = require('express');
const router = express.Router();
const dietgoalController = require('../controllers/dietgoal.controller');

// CREATE
router.post('/', dietgoalController.create);

// GET ALL
router.get('/', dietgoalController.findAll);


router.get('/user/:user_id', dietgoalController.findByUserId);
router.get('/diet/:diet_id', dietgoalController.findByDietId);
router.get('/start/:start_date', dietgoalController.findByStartDate);
router.get('/end/:end_date', dietgoalController.findByEndDate);

// GET BY ID (general route, must come last)
router.get('/:id', dietgoalController.findById);

// UPDATE
router.put('/:id', dietgoalController.update);

// UPDATE COMPLETION STATUS
router.put("/:id/completion", dietgoalController.updateCompletion);

// DELETE
router.delete('/:id', dietgoalController.delete);

module.exports = router;
