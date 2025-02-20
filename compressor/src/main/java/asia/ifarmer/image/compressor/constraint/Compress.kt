package asia.ifarmer.image.compressor.constraint

class Compress {
    internal var constraints: MutableList<Constraint> = mutableListOf()

    fun constraint(constraint: Constraint) {
        constraints.add(constraint)
    }


}