CREATE TABLE IF NOT EXISTS EXCHANGE (
  id INT AUTO_INCREMENT PRIMARY KEY,
  origin VARCHAR(50) NOT NULL,
  destine VARCHAR(50) NOT NULL,
  rate DECIMAL(4,2) NOT NULL
);