// userService.js
const pool = require('../config/db');


async function fetchUsersByEmail(email) {
    const [rows, fields] = await pool.execute('SELECT * FROM users where email = ?', [email]);
    return rows[0];
}

async function fetchUsers() {
    const [rows, fields] = await pool.execute('SELECT * FROM users');
    return rows;
}

async function fetchConversations() {
    const [rows, fields] = await pool.execute('SELECT * FROM ai_users_conversation');
    return rows;
}

async function fetchConversationById(id) {
    const [rows, fields] = await pool.execute('SELECT * FROM ai_users_conversation where user_id = ?', [id] );
    return rows;
}

module.exports = {
    fetchUsers,
    fetchConversations,
    fetchConversationById,
    fetchUsersByEmail
};