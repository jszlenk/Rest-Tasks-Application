#!/usr/bin/env bash

export URL="http://localhost:8080/tasks"

msg="The browser was successfully launched. Enjoys."

open_browser() {
  if xdg-open $URL; then
     end
  else
     fail
  fi
}

fail() {
  echo "There were errors compilation. Exit!"
}

end() {
  echo "Launching web browser: Done."
  echo "$(tput setaf 2)$(tput bold)${msg^^}$(tput sgr0)"
}

if ./runcrud.sh; then
   open_browser
else
   fail
fi