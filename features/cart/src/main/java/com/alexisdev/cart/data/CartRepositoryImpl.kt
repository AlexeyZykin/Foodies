package com.alexisdev.cart.data

import com.alexisdev.data.CartRepository
import com.alexisdev.database.dao.CartDao
import com.alexisdev.model.CartItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class CartRepositoryImpl(private val cartDao: CartDao) : CartRepository {
    override fun getCart(): Flow<List<CartItem>> {
        return cartDao.getAll()
            .map { cartItemsEntity ->
                cartItemsEntity.map { it.toCartItem() }
            }
    }

    override fun addCart(cartItem: CartItem) {
        cartDao.insert(cartItem.toCartItemEntity())
    }

    override fun removeCart(cartItem: CartItem) {
        val cartEntity = cartItem.toCartItemEntity()
        if (cartItem.quantity > 1)
            cartDao.update(cartEntity)
        else
            cartDao.delete(cartEntity)
    }
}