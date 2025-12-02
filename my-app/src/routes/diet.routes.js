const express = require('express')
const router = express.Router()
const dietController = require('../controllers/diet.controller');
const {verifyToken} = require("../middlewares/auth");

//get all users  
router.get('/',verifyToken, dietController.findAll);

//fetch all
router.get('/:id', dietController.findById);

//login  
router.post("/",dietController.login)

//login  
router.post("/create",dietController.create)


// Update password
router.post('/change_password', dietController.updatePassword);


// Updates the employee
router.put('/update', dietController.update);



module.exports = router