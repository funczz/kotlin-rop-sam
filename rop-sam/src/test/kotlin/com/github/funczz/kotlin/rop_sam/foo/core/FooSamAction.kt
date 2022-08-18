package io.kotlintest.provided.com.github.funczz.kotlin.rop_sam.foo.core

import com.github.funczz.kotlin.rop.result.RopResult
import com.github.funczz.kotlin.rop_sam.ISamAction

object FooSamAction : ISamAction<FooData, FooSamModel> {

    override fun execute(present: (FooData) -> RopResult<FooSamModel>, data: FooData): RopResult<FooSamModel> {
        return present(data)
    }

}