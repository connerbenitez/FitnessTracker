'use strict';
const dbConn = require('../../config/db.config');

class Diet {
    constructor(diet) {
        this.diet_id = diet.diet_id; 
        this.user_id = diet.user_id; 
        // this.diet_goal_id = diet.diet_goal_id;
        this.food_id = diet.food_id; 
        this.time = diet.time; 
        this.date = diet.date; 
    }

    // CREATE NEW DIET ENTRY
    static create(diet, result) {
        const insertData = {
            user_id: diet.user_id,
            // diet_goal_id: diet.diet_goal_id,
            food_id: diet.food_id,
            time: diet.time,
            date: diet.date
        };

        dbConn.query(
            "INSERT INTO diet SET ?", 
            insertData, 
            (err, res) => {
                if (err) return result(err, null);
                result(null, res.insertId);
            }
        );
    }

    // FIND BY ID
    static findById(diet_id, result) {
        dbConn.query(
            "SELECT * FROM diet WHERE diet_id = ?",
            [diet_id],
            (err, res) => {
                if (err) return result(err, null);
                result(null, res[0]);
            }
        );
    }

    // FIND ALL
    static findAll(result) {
        dbConn.query("SELECT * FROM diet", (err, res) => {
            if (err) return result(err, null);
            result(null, res);
        });
    }

    // FIND BY USER
    static findByUserId(user_id, result) {
        dbConn.query(
            "SELECT * FROM diet WHERE user_id = ?",
            [user_id],
            (err, res) => {
                if (err) return result(err, null);
                result(null, res);
            }
        );
    }

    // FIND BY FOOD
    static findByFoodId(food_id, result) {
        dbConn.query(
            "SELECT * FROM diet WHERE food_id = ?",
            [food_id],
            (err, res) => {
                if (err) return result(err, null);
                result(null, res);
            }
        );
    }

    // FIND BY DATE
    static findByDate(date, result) {
        dbConn.query(
            "SELECT * FROM diet WHERE date = ?",
            [date],
            (err, res) => {
                if (err) return result(err, null);
                result(null, res);
            }
        );
    }

    // UPDATE
    static update(diet_id, dietData, result) {
        const sql = `
            UPDATE diet
            SET user_id = ?, food_id = ?, time = ?, date = ?
            WHERE diet_id = ?
        `;

        const params = [
            dietData.user_id,
            dietData.food_id,
            dietData.time,
            dietData.date,
            diet_id
        ];

        dbConn.query(sql, params, (err, res) => {
            if (err) return result(err, null);
            result(null, res);
        });
    }

    // DELETE
    static delete(diet_id, result) {
        dbConn.query(
            "DELETE FROM diet WHERE diet_id = ?",
            [diet_id],
            (err, res) => {
                if (err) return result(err, null);
                result(null, res);
            }
        );
    }
}

module.exports = Diet;
