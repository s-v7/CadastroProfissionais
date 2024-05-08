/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  sv7
 * Created: 8 May 2024
 */

CREATE TABLE ProfessionalTitle (
    id SERIAL PRIMARY KEY,
    professional_id INTEGER REFERENCES Professional(id),
    title VARCHAR(100) NOT NULL
);