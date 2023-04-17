package co.selim.canvas

@JvmInline
value class Point(private val pos: Long) {

    constructor(x: Int, y: Int) : this(
        x.toLong().shl(32).or(y.toLong().and(0xFFFFFFFF))
    )

    val x: Int
        get() = pos.shr(32).toInt()

    val y: Int
        get() = pos.toInt()
}
