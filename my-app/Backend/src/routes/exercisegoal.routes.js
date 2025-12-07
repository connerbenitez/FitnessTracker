const express = require("express");
const router = express.Router();

const exerciseGoalController = require("../controllers/exercisegoal.controller");

// CREATE
router.post("/", exerciseGoalController.create);

// GET ALL
router.get("/", exerciseGoalController.findAll);


router.get("/user/:user_id", exerciseGoalController.findByUserId);

// GET BY ID
router.get("/:id", exerciseGoalController.findById);

// UPDATE
router.put("/:id", exerciseGoalController.update);

// DELETE
router.delete("/:id", exerciseGoalController.delete);

module.exports = router;
