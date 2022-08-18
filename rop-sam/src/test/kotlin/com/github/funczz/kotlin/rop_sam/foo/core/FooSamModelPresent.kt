package io.kotlintest.provided.com.github.funczz.kotlin.rop_sam.foo.core

import com.github.funczz.kotlin.rop.result.RopResult
import com.github.funczz.kotlin.rop_sam.ISamModelPresent

object FooSamModelPresent : ISamModelPresent<FooData, FooSamModel> {

    override fun present(data: FooData): RopResult<FooSamModel> {
        return RopResult.tee {
            FooSamModel(
                model = FooModel().also {
                    it.set(data.value)
                }
            )
        }
    }

}