package com.github.funczz.kotlin.rop_sam

import com.github.funczz.kotlin.rop.result.RopResult

/**
 * SAM Model Present インターフェイス:
 * SAM Present 関数を定義するクラス。
 * SAM Action 入力データ から SAM モデルへの変換を行う。
 * 永続化などの副作用を伴う処理はここに含める。
 * @param D Any: SAM Action 入力データ
 * @param M ISamModel: SAM モデル
 * @author funczz
 */
interface ISamModelPresent<D, M : ISamModel> {

    /**
     * SAM モデルを生成する。
     * @param data SAM Action 入力データ
     * @return RopResult M - ISamModel: SAM モデル
     */
    fun present(data: D): RopResult<M>

}