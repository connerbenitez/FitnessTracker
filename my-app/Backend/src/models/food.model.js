'use strict';
const dbConn = require('../../config/db.config');

class Food {
    constructor(food) {
        this.food_id = food.food_id;
        this.food_name = food.food_name;
        this.calories = food.calories;
        
    }

    static create(food, result) {
        const insertData = {
            food_name: food.food_name,
            calories: food.calories
        };
        dbConn.query("INSERT INTO food SET ?", insertData, (err, res) => {
            if (err) return result(err, null);
            result(null, res.insertId);
        });
    }

    static findById(id, result) {
        dbConn.query("SELECT * FROM food WHERE food_id = ?", [id], (err, res) => {
            if (err) return result(err, null);
            result(null, res);
        });
    }

    static findAll(result) {
        dbConn.query("SELECT * FROM food", (err, res) => {
            if (err) return result(err, null);
            result(null, res);
        });
    }

    static findByFoodName(food_name, result) {
        dbConn.query("SELECT * FROM food WHERE food_name = ?", [food_name], (err, res) => {
            if (err) return result(err, null);
            result(null, res);
        });
    }

    static findByCalories(calories, result) {
        dbConn.query("SELECT * FROM food WHERE calories = ?", [calories], (err, res) => {
            if (err) return result(err, null);
            result(null, res);
        });
    }

    static update(food_id, newFood, result) {
        dbConn.query(
            "UPDATE food SET food_name = ?, calories = ? WHERE food_id = ?",
            [newFood.food_name, newFood.calories, food_id],
            (err, res) => {
                if (err) return result(err, null);
                result(null, res);
            }
        );
    }

    
    
}

module.exports = Food;
