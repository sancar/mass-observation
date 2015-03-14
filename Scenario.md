# Scenario #

## Observation event ##

This observation event is about Boğaziçi University Computer Engineering Department. The scenario consists of three different perspectives for each of the roles in the system, namely, Initiator, Observer and Consumer. İrfan, Oğuz and Ceyda take the roles respectively.

## Initiator (İrfan) ##

Administration of İzmir High School of Science wants to be informed, and inform their students about Boğaziçi University Computer Engineering Department, while the students are starting to choose universities and departments. So İrfan, the administrator of the school, decides to start an OE for this purpose.

  * **Initial Assumption**
> İrfan (the administrator) has logged on to the web interface.

  * **Normal**
> İrfan creates an observation event and defines observers that are associated to Boğaziçi University (students, faculty, alumni, etc) and consumers as everyone (public). Then, lists the questions (what are classrooms, labs like, how is the campus life, in which projects do the students participate in, what do the students do after completion). Selects the type of possible observations as text, photo, video, voice record. Then starts the OE.

  * **What can go wrong**
> Due to a connection problem, the user may fail to complete the creation of the OE. In such a case, the system should save where the user is left off, to make him able to continue after the connection is reestablished.

  * **System state on completion**
> Irfan is logged on. There is a new OE in the system having the defined properties, which the defined observers are able to see.

## Observer (Oğuz) ##

Oğuz, a senior student in Boğaziçi University Computer Engineering Department, surfs the internet using his phone. He decides to check if he has any OE that he can participate in, using MA.

  * **Initial Assumption**
> MA is installed to the cellular phone of Oğuz (the observer), which has a connection to internet via wifi or computer access. The observer is also logged on.
  * **Normal**
> Oğuz lists the OE’s that he can participate in and finds the OE that the initiator has started earlier. He likes the idea to make observations about his school and share them, so he decides to participate in. He then makes observations: He takes photos of the building of the department, the classes, the labs. He takes records about the works that are done in labs and also he takes records about the interviews that are done with the people who are studying/teaching in the department. He records a video of robots playing soccer in the AI lab. Then he uploads the observations to the system.
  * **What can go wrong**
> If the connection is lost, Oğuz should upload his observations to the system once the connection is reestablished.
  * **System state after completion**
> There is a new observation linked to the OE and every user can see the observation as it is decided by the initiator.

## Consumer (Ceyda) ##

Ceyda is a counseling teacher in Kadıköy Anatolian High School. Ceyda is preparing a booklet to help the students make their decisions about the universities and departments. So Ceyda is currently making a lot of research about many different universities. So she checks the system if there is an OE about some universities. She finds the OE about Boğaziçi University Computer Engineering Department and decides to use those observations.

  * **Initial Assumption**
> Ceyda (Consumer) is logged on to the web interface.
  * **Normal**
> She searches the OE that she was looking for. The observations are categorized according to their types (photos, videos, notes). She views the observations that are done previously. She decides to first go through some textual information. She closes other types of observations and takes a glimpse at notes and comments. Then she looks at the photos. Chooses some of them to include in her booklet. She gives scores to these photos according to their quality and usefulness. Then she looks at other types of observations and sees the robot soccer video. She finds it interesting to show her students and downloads it as well. She gives a high score to this video and writes a comment to thank Oğuz for this helpful and informative video.
  * **What can go wrong**
> She can lose connection when she was viewing the observations. She needs to log in back to see the observations.
  * **System state after completion**
> The scores and her comment is added to the corresponding observation items.