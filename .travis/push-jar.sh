#!/bin/sh

setup() {
  echo "setup!"
  git config --global user.email "travis@travis-ci.org"
  git config --global user.name "Travis CI"
}

commit() {
  git add runnable/
  git commit --message "Travis build: $TRAVIS_BUILD_NUMBER"
}

push() {
   git push
}

setup
commit
push