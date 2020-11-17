
		pipeline {
		  agent any

		  stages {
			stage("One") {
			  steps {
				echo "Hello"
			  }
			}
			stage("Evaluate Master") {
			  when {
				// skip this stage unless on Master branch
				branch "master"
			  }
			  steps {
				echo "World"
				echo "Heal it"
			  }
			}
		  }
		}

-----------------

		pipeline {
		  agent any

		  stages {
			stage("Hello") {
			  steps {
				echo "Hello"
			  }
			}
			stage("Branch Test") {
			  when {
				// skip this stage unless branch is NOT master
				not {
				  branch "master"
				}
			  }
			  steps {
				echo "World"
				echo "Heal it"
			  }
			}
		  }
		}

-----------------------------

		pipeline {
		  agent any

		  stages {
			stage("One") {
			  steps {
				echo "Hello"
			  }
			}
			stage("Two") {
			  when {
				expression {
				  // "expression" can be any Groovy expression
				  return false
				}
			  }
			  steps {
				echo "World"
			  }
			}
			stage("Three") {
			  // This will show what a skipped stage followed by an unskipped stage looks like in Blue Ocean
			  steps {
				echo "Other World"
			  }
			}
		  }
		}


-----------------------------

		pipeline {
		  agent any
		  stages {
			stage("Hello") {
			  steps {
				echo "Hello"
			  }
			}
			stage("Always Skip") {
			  when {
				// skip this stage unless the expression evaluates to 'true'
				expression {
				  echo "Should I run?"
				  return false
				}
			  }
			  steps {
				echo "World"
			  }
			}
		  }
		}

-----------------------------

		pipeline {
		  agent any

		  environment {
			  // This returns 0 or 1 depending on whether build number is even or odd
			  FOO = "${currentBuild.getNumber() % 2}"
		  }

		  stages {
			stage("Hello") {
			  steps {
				echo "Hello"
			  }
			}
			stage("Evaluate FOO") {
			  when {
				// stage won't be skipped as long as FOO == 0, build number is even
				environment name: "FOO", value: "0"
			  }
			  steps {
				echo "World"
			  }
			}
		  }
		}
