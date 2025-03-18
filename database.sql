CREATE TABLE IF NOT EXISTS "users" (
                                       "id" serial NOT NULL UNIQUE,
                                       "email" varchar(255) NOT NULL,
                                       "password" bigint NOT NULL,
                                       "first_name" varchar(255) NOT NULL,
                                       "last_name" varchar(255) NOT NULL,
                                       "role" varchar(255) NOT NULL,
                                       PRIMARY KEY ("id")
);

CREATE TABLE IF NOT EXISTS "addresses" (
                                           "id" serial NOT NULL UNIQUE,
                                           "user_id" bigint NOT NULL,
                                           "country" varchar(255) NOT NULL,
                                           "state" varchar(255) NOT NULL,
                                           "city" varchar(255) NOT NULL,
                                           "street" varchar(255) NOT NULL,
                                           "post_code" varchar(255) NOT NULL,
                                           "phone_num" varchar(255) NOT NULL,
                                           "is_default" boolean NOT NULL,
                                           PRIMARY KEY ("id")
);

CREATE TABLE IF NOT EXISTS "categories" (
                                            "id" serial NOT NULL UNIQUE,
                                            "name" varchar(255) NOT NULL,
                                            PRIMARY KEY ("id")
);

CREATE TABLE IF NOT EXISTS "products" (
                                          "id" serial NOT NULL UNIQUE,
                                          "name" varchar(25) NOT NULL,
                                          "description" varchar(250) NOT NULL,
                                          "category_id" bigint NOT NULL,
                                          "price" varchar(255) NOT NULL,
                                          "avail_amount" bigint NOT NULL,
                                          "discount" bigint NOT NULL,
                                          "created_date" date NOT NULL,
                                          PRIMARY KEY ("id")
);

CREATE TABLE IF NOT EXISTS "carts" (
                                       "id" serial NOT NULL UNIQUE,
                                       "user_id" bigint NOT NULL,
                                       "product_id" bigint NOT NULL,
                                       "amount" bigint NOT NULL,
                                       PRIMARY KEY ("id")
);

CREATE TABLE IF NOT EXISTS "images" (
                                        "id" serial NOT NULL UNIQUE,
                                        "product_id" bigint NOT NULL,
                                        "img_url" varchar(255) NOT NULL,
                                        "is_main" boolean NOT NULL,
                                        PRIMARY KEY ("id")
);

CREATE TABLE IF NOT EXISTS "wishlists" (
                                           "id" serial NOT NULL UNIQUE,
                                           "user_id" bigint NOT NULL,
                                           "product_id" bigint NOT NULL,
                                           PRIMARY KEY ("id")
);

CREATE TABLE IF NOT EXISTS "feedbacks" (
                                           "id" serial NOT NULL UNIQUE,
                                           "product_id" bigint NOT NULL,
                                           "user_id" bigint NOT NULL,
                                           "feedback" varchar(250) NOT NULL,
                                           PRIMARY KEY ("id")
);

CREATE TABLE IF NOT EXISTS "notifications" (
                                               "id" serial NOT NULL UNIQUE,
                                               "user_id" bigint NOT NULL,
                                               "status" varchar(255) NOT NULL,
                                               "text" varchar(100) NOT NULL,
                                               PRIMARY KEY ("id")
);

CREATE TABLE IF NOT EXISTS "coupons" (
                                         "id" serial NOT NULL UNIQUE,
                                         "name" varchar(255) NOT NULL,
                                         "discount" bigint NOT NULL,
                                         "user_id" bigint NOT NULL,
                                         "expiraton_date" date NOT NULL,
                                         "is_used" boolean NOT NULL,
                                         PRIMARY KEY ("id")
);

CREATE TABLE IF NOT EXISTS "order_details" (
                                               "id" serial NOT NULL UNIQUE,
                                               "order_id" bigint NOT NULL,
                                               "product_id" bigint NOT NULL,
                                               "amount" bigint NOT NULL,
                                               "price" varchar(255) NOT NULL,
                                               PRIMARY KEY ("id")
);

CREATE TABLE IF NOT EXISTS "orders" (
                                        "id" serial NOT NULL UNIQUE,
                                        "user_id" bigint NOT NULL,
                                        "creation_date" date NOT NULL,
                                        "total_price" varchar(255) NOT NULL,
                                        "status" varchar(255) NOT NULL,
                                        PRIMARY KEY ("id")
);


ALTER TABLE "addresses" ADD CONSTRAINT "addresses_fk1" FOREIGN KEY ("user_id") REFERENCES "users"("id");

ALTER TABLE "products" ADD CONSTRAINT "products_fk3" FOREIGN KEY ("category_id") REFERENCES "categories"("id");

ALTER TABLE "carts" ADD CONSTRAINT "carts_fk1" FOREIGN KEY ("user_id") REFERENCES "users"("id");
ALTER TABLE "carts" ADD CONSTRAINT "carts_fk2" FOREIGN KEY ("product_id") REFERENCES "products"("id");

ALTER TABLE "images" ADD CONSTRAINT "images_fk1" FOREIGN KEY ("product_id") REFERENCES "products"("id");

ALTER TABLE "wishlists" ADD CONSTRAINT "wishlists_fk1" FOREIGN KEY ("user_id") REFERENCES "users"("id");

ALTER TABLE "wishlists" ADD CONSTRAINT "wishlists_fk2" FOREIGN KEY ("product_id") REFERENCES "products"("id");

ALTER TABLE "feedbacks" ADD CONSTRAINT "feedbacks_fk1" FOREIGN KEY ("product_id") REFERENCES "products"("id");
ALTER TABLE "feedbacks" ADD CONSTRAINT "feedbacks_fk2" FOREIGN KEY ("user_id") REFERENCES "users"("id");

ALTER TABLE "notifications" ADD CONSTRAINT "notifications_fk1" FOREIGN KEY ("user_id") REFERENCES "users"("id");

ALTER TABLE "coupons" ADD CONSTRAINT "coupons_fk3" FOREIGN KEY ("user_id") REFERENCES "users"("id");

ALTER TABLE "order_details" ADD CONSTRAINT "order_details_fk1" FOREIGN KEY ("order_id") REFERENCES "orders"("id");
ALTER TABLE "order_details" ADD CONSTRAINT "order_details_fk2" FOREIGN KEY ("product_id") REFERENCES "products"("id");

ALTER TABLE "orders" ADD CONSTRAINT "orders_fk1" FOREIGN KEY ("user_id") REFERENCES "users"("id");