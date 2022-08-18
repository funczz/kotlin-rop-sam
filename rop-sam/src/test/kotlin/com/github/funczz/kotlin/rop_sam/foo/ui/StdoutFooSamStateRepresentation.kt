package io.kotlintest.provided.com.github.funczz.kotlin.rop_sam.foo.ui

import com.github.funczz.kotlin.rop.result.RopResult
import com.github.funczz.kotlin.rop_sam.ISamStateRepresentation
import io.kotlintest.provided.com.github.funczz.kotlin.rop_sam.foo.core.FooSamModel
import io.kotlintest.provided.com.github.funczz.kotlin.rop_sam.foo.core.IFooSamState

object StdoutFooSamStateRepresentation : IFooSamState, ISamStateRepresentation<FooSamModel, Unit, String> {

    override fun representation(model: FooSamModel, representationData: Unit): RopResult<String> {
        return RopResult.tee {
            when (isBlank(model)) {
                true -> {
                    "ERROR".also {
                        System.err.println(it)
                        it
                    }
                }
                else -> {
                    model.model.value.also {
                        println(it)
                        it
                    }
                }
            }
        }
    }

}