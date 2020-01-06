/**
 * 
 */
app.controller("baseController",function($scope){
			//分页控件配置 
			$scope.paginationConf = {
					 currentPage: 1,
					 totalItems: 10,
					 itemsPerPage: 10,
					 perPageOptions: [10, 20, 30, 40, 50],
					 onChange: function(){
					        	 $scope.reloadList();//重新加载
					 }
			};
			$scope.reloadList=function(){
				$scope.search($scope.paginationConf.currentPage,$scope.paginationConf.itemsPerPage);
			}
			$scope.jsonToString=function(jsonString,key){
				var json=JSON.parse(jsonString);//将json字符串转换为json对象
				var value="";
				for(var i=0;i<json.length;i++){		
					if(i>0){
						value+="," 
					}
					value+=json[i][key];			
				}
				return value;
			}
			$scope.ids=[];
			$scope.selectedId=function($event,id){
				if($event.target.checked){
					$scope.ids.push(id);
				}else{
					var idx=$scope.ids.indexOf(id);
					$scope.ids.splice(idx,1);
				}
			}
			$scope.selectedAll=function($event){
			      if ($scope.checked) {
			          //可以没有,这个是获取所有id的  
			    	  for (var i = 0; i < $scope.list.length; i++) {
			                $scope.selectIds.push($scope.list[i].id);
			            }
			        } else {
			            $scope.selectIds = [];
			        }
			}
		});