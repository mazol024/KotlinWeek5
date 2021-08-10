package board

open class SquareBoardClass(override val width: Int) :SquareBoard {
    open val cells: ArrayList<Cell> = ArrayList()
    init {
        for (x in 1..width) {
            for (y in 1..width) {
                cells.add(Cell(x,y))
            }
        }
    }

    override fun getCellOrNull(i: Int, j: Int): Cell? = if (i * j > cells.size  )  null else  cells.get( width*(i-1) + j - 1)

    override fun getCell(i: Int, j: Int): Cell {
        if (i * j > cells.size  ) throw IllegalArgumentException("Your cell is out of board")
        return cells.get( width*(i-1) + j - 1)
    }

    override fun getAllCells(): Collection<Cell> = cells

    override fun getRow(i: Int, jRange: IntProgression): List<Cell> {
        var cellslist : MutableList<Cell> = mutableListOf<Cell>()
        loop@ for ( j in  jRange) {
            if ( j in  1..width) {
                cellslist.add(cells.get(width*(i-1) + j - 1))
            } else break@loop
        }
        return cellslist
    }

    override fun getColumn(iRange: IntProgression, j: Int): List<Cell> {
        var cellslist : MutableList<Cell> = mutableListOf<Cell>()
        loop@ for ( i in  iRange) {
            if ( i in  1..width) {
                cellslist.add(cells.get(width*(i-1) + j - 1))
            } else break@loop
        }
        return cellslist
    }

    override fun Cell.getNeighbour(direction: Direction): Cell? {
        return when {
            direction == Direction.LEFT -> if (getCell(this.i, this.j).j == 1) return null else return getCell(
                this.i,
                this.j - 1
            )
            direction == Direction.RIGHT -> if (getCell(
                    this.i,
                    this.j
                ).j == width
            ) return null else return getCell(this.i, this.j + 1)
            direction == Direction.DOWN -> if (getCell(
                    this.i,
                    this.j
                ).i == width
            ) return null else return getCell(this.i + 1, this.j)
            direction == Direction.UP -> if (getCell(this.i, this.j).i == 1) return null else return getCell(
                this.i - 1,
                this.j
            )
            else -> return  null
        }

    }

}