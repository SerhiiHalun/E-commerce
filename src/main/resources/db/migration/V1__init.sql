-- 1. users – данные пользователей
CREATE TABLE users (
       id INT PRIMARY KEY AUTO_INCREMENT,
       first_name VARCHAR(50) NOT NULL,
       last_name VARCHAR(50) NOT NULL,
       email VARCHAR(100) UNIQUE NOT NULL,
       password_hash TEXT NOT NULL,
       phone_number VARCHAR(20),
       role VARCHAR(20) DEFAULT 'customer',
       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 2. categories – иерархия категорий
CREATE TABLE categories (
        id INT PRIMARY KEY AUTO_INCREMENT,
        name VARCHAR(100) UNIQUE NOT NULL,
        parent_id INT,
        CONSTRAINT fk_categories_parent
            FOREIGN KEY (parent_id)
                REFERENCES categories(id)
                ON DELETE SET NULL
);

-- 3. products – товары
CREATE TABLE products (
      id INT PRIMARY KEY AUTO_INCREMENT,
      name VARCHAR(100) NOT NULL,
      description TEXT,
      price DECIMAL(10,2) NOT NULL,
      discount_price DECIMAL(10,2),
      stock INT NOT NULL DEFAULT 0,
      category_id INT,
      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
      CONSTRAINT fk_products_category
          FOREIGN KEY (category_id)
              REFERENCES categories(id)
              ON DELETE SET NULL
);

-- 4. product_images – изображения товаров
CREATE TABLE product_images (
        id INT PRIMARY KEY AUTO_INCREMENT,
        product_id INT NOT NULL,
        image_url TEXT NOT NULL,
        is_main BOOLEAN DEFAULT FALSE,
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        CONSTRAINT fk_product_images_product
            FOREIGN KEY (product_id)
                REFERENCES products(id)
                ON DELETE CASCADE
);

-- 5. orders – заказы
CREATE TABLE orders (
        id INT PRIMARY KEY AUTO_INCREMENT,
        user_id INT NOT NULL,
        total_price DECIMAL(10,2) NOT NULL,
        status VARCHAR(50) NOT NULL DEFAULT 'pending',
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

-- Уносим CHECK в отдельную строку-constraint
        CONSTRAINT chk_orders_status
            CHECK (status IN ('pending','shipped','delivered','canceled')),

        CONSTRAINT fk_orders_user
            FOREIGN KEY (user_id)
                REFERENCES users(id)
                ON DELETE CASCADE
);

-- 6. order_items – состав заказа
CREATE TABLE order_items (
             id INT PRIMARY KEY AUTO_INCREMENT,
             order_id INT NOT NULL,
             product_id INT NOT NULL,
             quantity INT NOT NULL,
             price DECIMAL(10,2) NOT NULL, -- Цена товара на момент покупки

             CONSTRAINT chk_order_items_quantity
                 CHECK (quantity > 0),

             CONSTRAINT fk_orderitems_order
                 FOREIGN KEY (order_id)
                     REFERENCES orders(id)
                     ON DELETE CASCADE,
             CONSTRAINT fk_orderitems_product
                 FOREIGN KEY (product_id)
                     REFERENCES products(id)
                     ON DELETE CASCADE
);

-- 7. payments – информация о платежах
CREATE TABLE payments (
          id INT PRIMARY KEY AUTO_INCREMENT,
          order_id INT NOT NULL,
          user_id INT NOT NULL,
          amount DECIMAL(10,2) NOT NULL,
          payment_method VARCHAR(50) NOT NULL,
          status VARCHAR(50) NOT NULL DEFAULT 'pending',
          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

-- Уносим CHECK в отдельную строку
          CONSTRAINT chk_payments_status
              CHECK (status IN ('pending','completed','failed')),

          CONSTRAINT fk_payments_order
              FOREIGN KEY (order_id)
                  REFERENCES orders(id)
                  ON DELETE CASCADE,
          CONSTRAINT fk_payments_user
              FOREIGN KEY (user_id)
                  REFERENCES users(id)
                  ON DELETE CASCADE
);

-- 8. addresses – адреса доставки
CREATE TABLE addresses (
           id INT PRIMARY KEY AUTO_INCREMENT,
           user_id INT NOT NULL,
           street VARCHAR(255) NOT NULL,
           city VARCHAR(100) NOT NULL,
           state VARCHAR(100),
           postal_code VARCHAR(20) NOT NULL,
           country VARCHAR(100) NOT NULL,

           CONSTRAINT fk_addresses_user
               FOREIGN KEY (user_id)
                   REFERENCES users(id)
                   ON DELETE CASCADE
);

-- 9. product_reviews – отзывы о товарах
CREATE TABLE product_reviews (
         id INT PRIMARY KEY AUTO_INCREMENT,
         product_id INT NOT NULL,
         user_id INT NOT NULL,
         rating INT NOT NULL,
         comment TEXT,
         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

-- Аналогично – выносим CHECK в отдельное ограничение
         CONSTRAINT chk_reviews_rating
             CHECK (rating BETWEEN 1 AND 5),

         CONSTRAINT fk_reviews_product
             FOREIGN KEY (product_id)
                 REFERENCES products(id)
                 ON DELETE CASCADE,
         CONSTRAINT fk_reviews_user
             FOREIGN KEY (user_id)
                 REFERENCES users(id)
                 ON DELETE CASCADE
);

-- 10. carts – корзины
CREATE TABLE carts (
       id INT PRIMARY KEY AUTO_INCREMENT,
       user_id INT NOT NULL,
       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

       CONSTRAINT fk_carts_user
           FOREIGN KEY (user_id)
               REFERENCES users(id)
               ON DELETE CASCADE
);

-- 11. cart_items – товары в корзине
CREATE TABLE cart_items (
            id INT PRIMARY KEY AUTO_INCREMENT,
            cart_id INT NOT NULL,
            product_id INT NOT NULL,
            quantity INT NOT NULL,
            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

            CONSTRAINT chk_cart_items_quantity
                CHECK (quantity > 0),

            CONSTRAINT fk_cartitems_cart
                FOREIGN KEY (cart_id)
                    REFERENCES carts(id)
                    ON DELETE CASCADE,
            CONSTRAINT fk_cartitems_product
                FOREIGN KEY (product_id)
                    REFERENCES products(id)
                    ON DELETE CASCADE
);
