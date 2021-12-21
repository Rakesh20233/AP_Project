path_to_files = ./src/main/java/com/example/programming_project/
path_to_target = ./target/classes/com/example/programming_project/

compileFiles = $(path_to_files)HelloApplication.java $(path_to_files)HelloController.java $(path_to_files)Scene2.java $(path_to_files)Scene3.java $(path_to_files)Scene4_Comp.java
buildFiles = $(path_to_target)HelloApplication.class $(path_to_target)HelloController.class $(path_to_target)Scene2.class $(path_to_target)Scene3.class $(path_to_target)Scene4_Comp.class

path_to_fx = /Library/Java/JavaVirtualMachines/javafx-sdk-17.0.1/lib/

javafx_Library = javafx.controls,javafx.fxml,javafx.media

BUILD_FLAGS = --module-path $(path_to_fx) --add-modules $(javafx_Library)

all: $(buildFiles)
	java $(BUILD_FLAGS) --class-path ./target/classes/ com.example.programming_project.HelloApplication

$(path_to_target)HelloApplication.class: $(path_to_files)HelloApplication.java
	javac $(BUILD_FLAGS) $(path_to_files)HelloApplication.java 

$(path_to_target)HelloController.class: $(path_to_files)HelloController.java
	javac $(BUILD_FLAGS) $(path_to_files)HelloController.java 

$(path_to_target)Scene2.class: $(path_to_files)Scene2.java
	javac $(BUILD_FLAGS) $(path_to_files)Scene2.java

$(path_to_target)Scene3.class: $(path_to_files)Scene3.java
	javac $(BUILD_FLAGS) $(path_to_files)Scene3.java

$(path_to_target)Scene4_Comp.class: $(path_to_files)Scene4_Comp.java
	javac $(BUILD_FLAGS) $(path_to_files)Scene4_Comp.java

clean: $(buildFiles)
	rm $(buildFiles)
