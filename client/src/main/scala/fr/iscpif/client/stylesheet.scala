package fr.iscpif.client

/*
 * Copyright (C) 01/04/16 // mathieu.leclaire@openmole.org
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

import fr.iscpif.scaladget.stylesheet.all._
import scalatags.JsDom.{styles => sty}
import scalatags.JsDom.all._

package object stylesheet {
  lazy val selectedButton: ModifierSeq = Seq(
    btn,
    sty.backgroundColor := "#e3dbdb;"
  )

  lazy val toto: ModifierSeq = Seq(
    position := "absolute",
    sty.top := 200,
    color := "green"
  )
}