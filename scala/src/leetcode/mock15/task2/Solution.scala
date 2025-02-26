package leetcode.mock15.task2

import scala.collection.{mutable => mu}

case class Folder(
    val name: String,
    var subFolders: List[Folder],
    var files: List[String]
)

object Solution {
  def lengthLongestPath(input: String): Int = {

    // dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext

    val inputList = input.split("\n").toList

    val levels = mu.Map.empty[Int, Folder]

    levels.addOne(-1, Folder("", List(), List()))

    for (line <- inputList) {
      val level = line.count(_ == '\t')
      val name = line.drop(level)
      val folder = levels(level - 1)

      if (line.contains('.')) {
        folder.files = name +: folder.files
      } else {
        folder.subFolders = Folder(name, List(), List()) +: folder.subFolders
        levels.update(level, folder.subFolders.head)
      }
    }
    val len = getMaxPath(levels(-1))
    if (len == 0) 0 else len - 1
  }

  def getMaxPath(folder: Folder): Int = {
    val sub =
      if (folder.subFolders.isEmpty) 0
      else folder.subFolders.map(getMaxPath(_)).max
    val files = if (folder.files.isEmpty) 0 else folder.files.map(_.length).max
    if (sub == 0 && files == 0) 0
    else
      Math.max(sub, files) + folder.name.length + 1
  }

}
