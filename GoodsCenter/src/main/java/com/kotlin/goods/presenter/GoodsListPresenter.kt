package com.kotlin.goods.presenter

import com.kotlin.base.ext.execute
import com.kotlin.base.presenter.BasePresenter
import com.kotlin.base.rx.BaseSubscriber
import com.kotlin.goods.data.protocol.Goods
import com.kotlin.goods.presenter.view.GoodsListView
import com.kotlin.goods.service.GoodsService
import javax.inject.Inject

/**
 * Create by Pidan
 */
class GoodsListPresenter @Inject constructor() : BasePresenter<GoodsListView>() {
    @Inject
    lateinit var goodsService: GoodsService

    fun getGoodsList(categoryId: Int, pageNo: Int) {
        if (!checkNetwork()) {
            return
        }
        mView.showLoading()
        goodsService.getGoodsList(categoryId, pageNo)
            .execute(lifecycleProvider, object : BaseSubscriber<MutableList<Goods>?>(mView) {
                override fun onNext(t: MutableList<Goods>?) {
                    mView.onGetGoodsListResult(t)
                }
            })
    }

    fun getGoodsListByKeyword(keyword: String, pageNo: Int) {
        if (!checkNetwork()) {
            return
        }
        mView.showLoading()
        goodsService.getGoodsListByKeyword(keyword, pageNo)
            .execute(lifecycleProvider, object : BaseSubscriber<MutableList<Goods>?>(mView) {
                override fun onNext(t: MutableList<Goods>?) {
                    mView.onGetGoodsListResult(t)
                }
            })
    }
}