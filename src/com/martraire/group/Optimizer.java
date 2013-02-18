package com.martraire.group;

import java.util.List;


public interface Optimizer {

	List<Command> optimize(List<Command> commands);

}