const express = require("express");
const router = express.Router();

const exerciseController = require("../controllers/exercise.controller");

// CREATE
router.post("/", exerciseController.create);

// GET ALL
router.get("/", exerciseController.findAll);


router.get("/user/:user_id", exerciseController.findByUserId);

// GET BY ID
router.get("/:id", exerciseController.findById);

// Get exercises by type
router.get("/type/:type", exerciseController.findByType);

// Get exercises by date
router.get("/date/:date", exerciseController.findByDate);

// UPDATE
router.put("/:id", exerciseController.update);

// DELETE
router.delete("/:id", exerciseController.delete);

module.exports = router;
