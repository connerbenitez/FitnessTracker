const express = require('express');
const bodyParser = require('body-parser');
const cors = require("cors");
const cookieParser = require("cookie-parser");
require("dotenv").config();

const app = express();
const port = process.env.PORT || 4001;

// Middleware
app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());
app.use(cookieParser());
app.use(cors({
    origin: ["http://localhost:3000","http://localhost:3001"],
    methods: ["GET", "POST", "PUT", "DELETE"], 
    credentials: true,
}));
app.use(express.static('public'));

// Root route
app.get('/', (req, res) => {
  res.send("Hello World");
});

// Routes
const userRoutes = require('./src/routes/user.routes');
const messageRoutes = require('./src/routes/message.routes');
const foodRoutes = require('./src/routes/food.routes');
const followerRoutes = require('./src/routes/follower.routes');
const exerciseGoalRoutes = require('./src/routes/exercisegoal.routes');

app.use('/api/users', userRoutes);
app.use('/api/messages', messageRoutes);
app.use('/api/foods', foodRoutes);
app.use('/api/followers', followerRoutes);
app.use('/api/exercise-goals', exerciseGoalRoutes);

// Cache control
app.use((req,res,next)=>{
  res.set('Cache-Control','no-store');
  next();
});

// Start server
app.listen(port, () => {
  console.log(`Server is listening on port ${port}`);  
});
