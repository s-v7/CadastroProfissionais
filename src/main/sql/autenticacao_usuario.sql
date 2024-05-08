/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  sv7
 * Created: 8 May 2024
 */

-- Script para criar a tabela de autenticação
CREATE TABLE autenticacao_usuario (
    id SERIAL PRIMARY KEY,
    nome_usuario VARCHAR(100),
    senha VARCHAR(255)
);